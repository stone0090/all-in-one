package com.stone0090.aio.service.core.algorithm.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.stone0090.aio.dao.mybatis.entity.ServiceDO;
import com.stone0090.aio.dao.mybatis.entity.ServiceDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ServiceDOMapper;
import com.stone0090.aio.manager.utils.HttpUtil;
import com.stone0090.aio.service.clients.K8sClient;
import com.stone0090.aio.service.common.Constants;
import com.stone0090.aio.service.core.algorithm.SvcCallback;
import com.stone0090.aio.service.enums.SvcStatusEnum;
import com.stone0090.aio.service.enums.DataTypeEnum;
import com.stone0090.aio.service.model.service.dag.DeployInfo;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("svcService")
public class SvcServiceImpl {

    @Autowired
    private ServiceDOMapper serviceDOMapper;
    @Autowired
    private K8sClient k8sClient;
    @Autowired
    private HttpUtil httpUtil;

    @Value("${spring.profiles.active}")
    private String env;

    public int online(ServiceDO serviceDO, SvcCallback callback) {
        ServiceDO oldServiceDO = getServiceByBizId(serviceDO.getSvcType(), serviceDO.getSvcBizId());
        if (oldServiceDO != null && SvcStatusEnum.ONLINE.name().equals(oldServiceDO.getSvcStatus())) {
            throw new RuntimeException("服务上线失败，不能重复上线！");
        }
        if (oldServiceDO != null) {
            serviceDO.setId(oldServiceDO.getId());
            serviceDO.setSvcUuid(oldServiceDO.getSvcUuid());
        }
        String resourceId = buildResourceId(serviceDO.getSvcType(), serviceDO.getSvcBizId());
        V1Deployment deployment = k8sClient.createDeployment(resourceId);
        V1Service service = k8sClient.createService(resourceId);
        String svcUrl = Constants.DEV.equals(env)
                ? "localhost:" + service.getSpec().getPorts().get(0).getNodePort()
                : resourceId + "-svc.aio:6000";
        serviceDO.setSvcUrl(svcUrl);
        // 部署和轮询检查服务状态，30秒超时
        deployAndHealthCheck(serviceDO, resourceId, callback);
        serviceDO.setSvcStatus(SvcStatusEnum.ONLINE.name());
        return save(serviceDO);
    }

    public int offline(String type, Integer typeId) {
        String resourceId = buildResourceId(type, typeId);
        releaseResource(resourceId);
        ServiceDO serviceDO = getServiceByBizId(type, typeId);
        if (serviceDO != null) {
            serviceDO.setSvcStatus(SvcStatusEnum.OFFLINE.name());
            return save(serviceDO);
        }
        return 1;
    }

    public String invoke(String type, Integer typeId, String inputParam) {
        ServiceDO serviceDO = getServiceByBizId(type, typeId);
        if (serviceDO == null || !SvcStatusEnum.ONLINE.name().equals(serviceDO.getSvcStatus())) {
            throw new RuntimeException("服务调用失败，服务不存在或未上线！");
        }
        try {
            Map<String, Object> ruleMap = JsonUtil.parseJson(serviceDO.getInputParam());
            Map<String, Object> realMap = JsonUtil.parseJson(inputParam);
            ruleMap.forEach((k, v) -> {
                Map<String, Object> ruleDetailMap = (Map<String, Object>) v;
                if (!DataTypeEnum.checkType(ruleDetailMap.get("type").toString(), realMap.get(k))) {
                    throw new RuntimeException("服务调用失败，入参[" + k + "]的类型不匹配，正确的类型为[" + ruleDetailMap.get("type").toString() + "]！");
                }
            });
        } catch (JoseException e) {
            throw new RuntimeException("服务调用失败，入参格式有误！");
        }
        String result = httpUtil.post(serviceDO.getSvcUrl() + "/aio/scheduler/faas/invoke", inputParam);
        RestResult restResult = JSON.parseObject(result, RestResult.class);
        return JSON.toJSONString(restResult, true);
    }

    public void deployAndHealthCheck(ServiceDO serviceDO, String resourceId, SvcCallback callback) {
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
                    log.info("scheduler健康检查[" + count + "]次，耗时[" + count * 500 + "]毫秒...");
                    result = httpUtil.get(serviceDO.getSvcUrl() + "/aio/scheduler/health/check");
                } catch (Exception e) {
                    log.warn("scheduler健康检查失败，url:" + serviceDO.getSvcUrl(), e);
                }
                if (Constants.SUCCESS.equals(result)) {
                    schedulerHealthCheck = true;
                    DeployInfo deployInfo = callback.buildDeployInfo();
                    result = httpUtil.post(serviceDO.getSvcUrl() + "/aio/scheduler/faas/deploy", JSON.toJSONString(deployInfo));
                    RestResult restResult = JSON.parseObject(result, RestResult.class);
                    if (!restResult.getSuccess()) {
                        releaseResource(resourceId);
                        throw new RuntimeException("服务上线失败，调用scheduler部署接口失败！");
                    }
                }
            } else {
                try {
                    log.info("faas健康检查[" + count + "]次，耗时[" + count * 500 + "]毫秒...");
                    result = httpUtil.get(serviceDO.getSvcUrl() + "/aio/scheduler/faas/health/check");
                } catch (Exception e) {
                    log.warn("faas健康检查失败，url:" + serviceDO.getSvcUrl(), e);
                }
                if (Constants.SUCCESS.equals(result)) {
                    faasHealthCheck = true;
                    break;
                }
            }
        }
        if (!schedulerHealthCheck || !faasHealthCheck) {
            releaseResource(resourceId);
            throw new RuntimeException("服务失败上线，健康检查失败！");
        }
    }

    public int save(ServiceDO data) {
        if (data.getId() == null) {
            return serviceDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return serviceDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    public ServiceDO getServiceByBizId(String type, Integer bizId) {
        List<ServiceDO> result = listServiceByBizIds(type, Lists.newArrayList(bizId));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<ServiceDO> listServiceByBizIds(String type, List<Integer> typeIds) {
        ServiceDOExample example = new ServiceDOExample();
        ServiceDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andSvcTypeEqualTo(type);
        criteria.andSvcBizIdIn(typeIds);
        return serviceDOMapper.selectByExample(example);
    }

    public int removeServiceByBizId(String type, Integer bizId) {
        ServiceDO serviceDO = getServiceByBizId(type, bizId);
        serviceDO.setIsDeleted((int) System.currentTimeMillis());
        ServiceDOExample example = new ServiceDOExample();
        ServiceDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andSvcTypeEqualTo(type);
        criteria.andSvcBizIdEqualTo(bizId);
        return serviceDOMapper.updateByExample(serviceDO, example);
    }

    public String buildResourceId(String svcType, Integer bizId) {
        return svcType + "-" + String.format("%06d", bizId);
    }

    private void releaseResource(String resourceId) {
        try {
            k8sClient.deleteDeployment(resourceId);
            k8sClient.deleteService(resourceId);
        } catch (Exception e) {
            log.warn("k8s资源删除失败，忽略", e);
        }
    }

}
