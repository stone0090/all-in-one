package com.stone0090.aio.service.core.algorithm.impl;

import com.google.common.collect.Lists;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.ApiDOExample;
import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ApiDOMapper;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDOMapper;
import com.stone0090.aio.manager.utils.HttpUtil;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.clients.K8sClient;
import com.stone0090.aio.service.common.Constants;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.ApiService;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.InvokeTypeEnum;
import com.stone0090.aio.service.model.web.request.ApiInvokeRequest;
import com.stone0090.aio.service.model.web.request.ApiRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDOMapper apiDOMapper;
    @Autowired
    private OperatorDOMapper operatorDOMapper;
    @Autowired
    private K8sClient k8sClient;
    @Autowired
    private HttpUtil httpUtil;

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public int online(ApiRequest request) {
        if (!AlgoTypeEnum.OPERATOR.name().equals(request.getApiType())) {
            throw new RuntimeException("API上线失败，仅支持算子发布成API！");
        }
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("API上线失败，算子不存在！");
        }
        ApiDO apiDO = getApiByTypeId(request.getApiType(), request.getId());
        if (apiDO != null && ApiStatusEnum.ONLINE.name().equals(apiDO.getStatus())) {
            throw new RuntimeException("API上线失败，已上线的API不能重复上线！");
        }
        apiDO = buildOperatorApiDO(operatorDO, apiDO);
        String resourceId = buildResourceId(apiDO.getApiType(), apiDO.getTypeId());
        // 启动k8s资源
        V1Deployment deployment = k8sClient.createDeployment(resourceId);
        V1Service service = k8sClient.createService(resourceId);
        apiDO.setApiUrl(Constants.DEV.equals(env)
                ? "localhost:" + service.getSpec().getPorts().get(0).getNodePort()
                : resourceId + "-svc.aio:6000");
        // 注入算法代码，轮询检查服务状态，30秒超时
        injectCodeAndHealthCheck(apiDO, resourceId, operatorDO.getAlgoCode());
        apiDO.setStatus(ApiStatusEnum.ONLINE.name());
        return save(apiDO);
    }

    @Override
    public int offline(ApiRequest request) {
        if (!AlgoTypeEnum.OPERATOR.name().equals(request.getApiType())) {
            throw new RuntimeException("API下线失败，仅支持算子发布成API！");
        }
        ApiDO apiDO = getApiByTypeId(AlgoTypeEnum.OPERATOR.name(), request.getId());
        if (apiDO == null) {
            throw new RuntimeException("API下线失败，API不存在！");
        }
        String resourceId = buildResourceId(apiDO.getApiType(), apiDO.getTypeId());
        try {
            k8sClient.deleteDeployment(resourceId);
            k8sClient.deleteService(resourceId);
        } catch (Exception e) {
            log.warn("k8s资源删除失败，忽略", e);
        }
        apiDO.setStatus(ApiStatusEnum.OFFLINE.name());
        return save(apiDO);
    }

    @Override
    public String invokeApi(ApiInvokeRequest request) {
        ApiDO apiDO = getApiByTypeId(request.getApiType(), request.getId());
        if (apiDO == null || !ApiStatusEnum.ONLINE.name().equals(apiDO.getStatus())) {
            throw new RuntimeException("API不存在或者API不是在线状态！");
        }
        // TODO：参数格式校验
        return httpUtil.post(apiDO.getApiUrl() + "/aio/scheduler/python/invoke", request.getInputParam());
    }

    private ApiDO buildOperatorApiDO(OperatorDO operatorDO, ApiDO apiDO) {
        if (apiDO == null) {
            apiDO = new ApiDO();
            apiDO.setApiUuid(UuidUtil.getUuid());
        }
        apiDO.setApiName(operatorDO.getOpName());
        apiDO.setApiType(AlgoTypeEnum.OPERATOR.name());
        apiDO.setTypeId(operatorDO.getId());
        apiDO.setInputParam(operatorDO.getInputParam());
        apiDO.setOutputParam(operatorDO.getOutputParam());
        apiDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        apiDO.setApiUrl("");
        apiDO.setCallbackUrl("");
        apiDO.setStatus(ApiStatusEnum.OFFLINE.name());
        return apiDO;
    }

    private int save(ApiDO data) {
        if (data.getId() == null) {
            return apiDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return apiDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    private void injectCodeAndHealthCheck(ApiDO apiDO, String resourceId, String algoCode) {
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
                    deployInfo.put("algoCode", algoCode);
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
            throw new RuntimeException("API失败上线，算子服务健康检查失败！");
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

    private String buildResourceId(String apiType, Integer typeId) {
        return apiType + "-" + leftPad(typeId);
    }

    private static String leftPad(Integer id) {
        String str = String.valueOf(id);
        if (str.length() < 6) {
            return String.format("%06d", Integer.parseInt(str));
        }
        return str;
    }

    public OperatorDO getOperatorDO(Integer id) {
        OperatorDOExample example = new OperatorDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }
}
