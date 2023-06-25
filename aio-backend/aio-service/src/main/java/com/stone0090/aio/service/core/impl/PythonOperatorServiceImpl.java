package com.stone0090.aio.service.core.impl;

import com.stone0090.aio.api.response.OperatorVO;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.clients.K8sClient;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stone
 * @date 2023/06/23
 */
@Service
public class PythonOperatorServiceImpl {

    @Autowired
    private K8sClient k8sClientService;

    public String createApi(OperatorVO operatorVO, ApiDO apiDO) {
        String bizId = AlgoTypeEnum.OPERATOR.name().toLowerCase() + "-" + leftPad(operatorVO.getId());
        V1Deployment deployment = k8sClientService.createDeployment(bizId);
        V1Service service = k8sClientService.createService(bizId);
        return null;
    }

    // 当字符串位数小于6位，左边补零，补齐到6位
    private String leftPad(Integer id) {
        String str = String.valueOf(id);
        if (str.length() < 6) {
            return String.format("%06d", Integer.parseInt(str));
        }
        return str;
    }

}
