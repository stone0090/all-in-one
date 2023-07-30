package com.stone0090.aio.service.core.algorithm.impl;

import com.google.common.collect.Lists;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.ApiDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ApiDOMapper;
import com.stone0090.aio.manager.utils.HttpUtil;
import com.stone0090.aio.service.clients.K8sClient;
import com.stone0090.aio.service.common.Constants;
import com.stone0090.aio.service.core.algorithm.ApiStartupCallback;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.DataTypeEnum;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("apiService")
public class ApiServiceImpl {

    @Autowired
    private ApiDOMapper apiDOMapper;
    @Autowired
    private K8sClient k8sClient;
    @Autowired
    private HttpUtil httpUtil;

    @Value("${spring.profiles.active}")
    private String env;

    public int online(ApiDO apiDO, ApiStartupCallback callback) {
        ApiDO oldApiDO = getApiByTypeId(apiDO.getApiType(), apiDO.getTypeId());
        if (oldApiDO != null && ApiStatusEnum.ONLINE.name().equals(oldApiDO.getApiStatus())) {
            throw new RuntimeException("API上线失败，不能重复上线！");
        }
        if (oldApiDO != null) {
            apiDO.setId(oldApiDO.getId());
            apiDO.setApiUuid(oldApiDO.getApiUuid());
        }
        String resourceId = buildResourceId(apiDO.getApiType(), apiDO.getTypeId());
        V1Deployment deployment = k8sClient.createDeployment(resourceId);
        V1Service service = k8sClient.createService(resourceId);
        String apiUrl = Constants.DEV.equals(env)
                ? "localhost:" + service.getSpec().getPorts().get(0).getNodePort()
                : resourceId + "-svc.aio:6000";
        apiDO.setApiUrl(apiUrl);
        // 注入算法代码，轮询检查服务状态，30秒超时
        injectAndHealthCheck(apiDO, resourceId, callback);
        apiDO.setApiStatus(ApiStatusEnum.ONLINE.name());
        return save(apiDO);
    }

    public int offline(String type, Integer typeId) {
        ApiDO apiDO = getApiByTypeId(type, typeId);
        if (apiDO == null || !ApiStatusEnum.ONLINE.name().equals(apiDO.getApiStatus())) {
            throw new RuntimeException("API下线失败，API不存在或未上线！");
        }
        String resourceId = buildResourceId(apiDO.getApiType(), apiDO.getTypeId());
        try {
            k8sClient.deleteDeployment(resourceId);
            k8sClient.deleteService(resourceId);
        } catch (Exception e) {
            log.warn("k8s资源删除失败，忽略", e);
        }
        apiDO.setApiStatus(ApiStatusEnum.OFFLINE.name());
        return save(apiDO);
    }

    public String invoke(String type, Integer typeId, String inputParam) {
        ApiDO apiDO = getApiByTypeId(type, typeId);
        if (apiDO == null || !ApiStatusEnum.ONLINE.name().equals(apiDO.getApiStatus())) {
            throw new RuntimeException("API调用失败，API不存在或未上线！");
        }
        try {
            Map<String, Object> ruleMap = JsonUtil.parseJson(apiDO.getInputParam());
            Map<String, Object> realMap = JsonUtil.parseJson(inputParam);
            ruleMap.forEach((k, v) -> {
                Map<String, Object> ruleDetailMap = (Map<String, Object>) v;
                if (!DataTypeEnum.checkType(ruleDetailMap.get("type").toString(), realMap.get(k))) {
                    throw new RuntimeException("API调用失败，入参[" + k + "]的类型不匹配，正确的类型为[" + ruleDetailMap.get("type").toString() + "]！");
                }
            });
        } catch (JoseException e) {
            throw new RuntimeException("API调用失败，入参格式有误！");
        }
        return httpUtil.post(apiDO.getApiUrl() + "/aio/scheduler/python/invoke", inputParam);
    }

    public void injectAndHealthCheck(ApiDO apiDO, String resourceId, ApiStartupCallback callback) {
        boolean schedulerHealthCheck = false;
        boolean faasHealthCheck = false;
        int count = 0;
        while (count++ < 60) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = Constants.FAILURE;
            if (!schedulerHealthCheck) {
                try {
                    result = httpUtil.get(apiDO.getApiUrl() + "/aio/scheduler/health/check");
                } catch (Exception e) {
                    log.warn("scheduler健康检查失败，url:" + apiDO.getApiUrl(), e);
                }
                if (Constants.SUCCESS.equals(result)) {
                    schedulerHealthCheck = true;
                    Map<String, String> deployInfo = new HashMap<>();
                    deployInfo.put("resourceId", resourceId);
                    callback.inject(deployInfo);
                    httpUtil.post(apiDO.getApiUrl() + "/aio/scheduler/python/deploy", JsonUtil.toJson(deployInfo));
                }
            } else {
                try {
                    result = httpUtil.get(apiDO.getApiUrl() + "/aio/scheduler/python/health/check");
                } catch (Exception e) {
                    log.warn("faas健康检查失败，url:" + apiDO.getApiUrl(), e);
                }
                if (Constants.SUCCESS.equals(result)) {
                    faasHealthCheck = true;
                    break;
                }
            }
        }
        if (!schedulerHealthCheck || !faasHealthCheck) {
            try {
                k8sClient.deleteDeployment(resourceId);
                k8sClient.deleteService(resourceId);
            } catch (Exception e) {
                log.warn("k8s资源删除失败，忽略", e);
            }
            throw new RuntimeException("API失败上线，健康检查失败！");
        }
    }

    public int save(ApiDO data) {
        if (data.getId() == null) {
            return apiDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return apiDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    public ApiDO getApiByTypeId(String type, Integer typeId) {
        List<ApiDO> result = listApiByTypeIds(type, Lists.newArrayList(typeId));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<ApiDO> listApiByTypeIds(String type, List<Integer> typeIds) {
        ApiDOExample example = new ApiDOExample();
        ApiDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andApiTypeEqualTo(type);
        criteria.andTypeIdIn(typeIds);
        return apiDOMapper.selectByExample(example);
    }

    public int removeApiByTypeId(String type, Integer typeId) {
        ApiDO apiDO = getApiByTypeId(type, typeId);
        apiDO.setIsDeleted((int) System.currentTimeMillis());
        ApiDOExample example = new ApiDOExample();
        ApiDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andApiTypeEqualTo(type);
        criteria.andTypeIdEqualTo(typeId);
        return apiDOMapper.updateByExample(apiDO, example);
    }

    public String buildResourceId(String apiType, Integer typeId) {
        return apiType + "-" + String.format("%06d", typeId);
    }

}
