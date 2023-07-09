package com.stone0090.aio.service.core.impl;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.ApiQueryRequest;
import com.stone0090.aio.api.request.ApiRequest;
import com.stone0090.aio.api.request.ApiSaveRequest;
import com.stone0090.aio.api.request.IdRequest;
import com.stone0090.aio.api.response.ApiVO;
import com.stone0090.aio.api.response.OperatorVO;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.ApiDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ApiDOMapper;
import com.stone0090.aio.manager.utils.HttpUtil;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.clients.K8sClient;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.ApiService;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.InvokeTypeEnum;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDOMapper apiDOMapper;
    @Autowired
    private K8sClient k8sClientService;
    @Autowired
    private HttpUtil httpUtil;

    @Override
    public PageResult<ApiVO> list(ApiQueryRequest queryRequest, PageRequest pageRequest) {
        return null;
    }

    @Override
    public ApiVO get(IdRequest request) {
        return null;
    }

    @Override
    public int save(ApiSaveRequest request) {
        ApiDO data = Converter.toApiDO(request);
        return save(data);
    }

    @Override
    public int remove(IdRequest request) {
        return 0;
    }

    public int save(ApiDO data) {
        if (data.getId() == null) {
            return apiDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return apiDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    public ApiDO getByType(String type, Integer typeId) {
        ApiDOExample example = new ApiDOExample();
        ApiDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andApiTypeEqualTo(type);
        criteria.andTypeIdEqualTo(typeId);
        List<ApiDO> result = apiDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public int publishApi(IdRequest request) {
        ApiDO apiDO = getByType(AlgoTypeEnum.OPERATOR.name(), request.getId());
        if (apiDO == null) {
            apiDO = new ApiDO();
            apiDO.setApiCode(UuidUtil.getUuid());
        }
        apiDO.setApiName(request.getId() + "服务");
        apiDO.setApiType(AlgoTypeEnum.OPERATOR.name());
        apiDO.setTypeId(request.getId());
        apiDO.setApiUrl("");
        apiDO.setInputParam("");
        apiDO.setOutputParam("");
        apiDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        apiDO.setCallbackUrl("");
        apiDO.setStatus(ApiStatusEnum.PUBLISHING.name());
        save(apiDO);
        String bizId = AlgoTypeEnum.OPERATOR.name().toLowerCase() + "-" + leftPad(request.getId());
        V1Deployment deployment = k8sClientService.createDeployment(bizId);
        V1Service service = k8sClientService.createService(bizId);
//        apiDO.setApiUrl(service.getSpec().getClusterIP() + ":" + service.getSpec().getPorts().get(0).getPort());
//        apiDO.setApiUrl(bizId + "-svc.aio:6000");
        apiDO.setApiUrl("localhost:" + service.getSpec().getPorts().get(0).getNodePort());
        apiDO.setStatus(ApiStatusEnum.PUBLISHED.name());
        return save(apiDO);
    }

    @Override
    public String invokeApi(ApiRequest request) {
        ApiDO apiDO = getByType(AlgoTypeEnum.OPERATOR.name(), request.getId());
        if (apiDO == null) {
            return null;
        }
        return httpUtil.get(apiDO.getApiUrl() + "/health/status");
//        return httpUtil.post(apiDO.getApiUrl() + "/python/invoke", request.getPostDate());
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
