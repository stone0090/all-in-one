package com.stone0090.aio.service.core.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.OperatorVO;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDOMapper;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.common.ConfigConstants;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.OperatorService;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.enums.InvokeTypeEnum;
import com.stone0090.aio.service.system.impl.ConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stone
 * @date 2023/06/22
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDOMapper operatorDOMapper;
    @Autowired
    private ApiServiceImpl apiServiceImpl;
    @Autowired
    private ConfigServiceImpl configServiceImpl;
    @Autowired
    private PythonOperatorServiceImpl pythonOperatorServiceImpl;

    @Override
    public PageResult<OperatorVO> list(OperatorQueryRequest queryRequest, PageRequest pageRequest) {
        OperatorDOExample example = new OperatorDOExample();
        OperatorDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getOpCode())) {
            criteria.andOpCodeLike("%" + queryRequest.getOpCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getOpName())) {
            criteria.andOpNameLike("%" + queryRequest.getOpName() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        PageResult<OperatorVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toOperatorVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public OperatorVO get(IdRequest request) {
        OperatorDOExample example = new OperatorDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toOperatorVO(result.get(0));
    }

    @Override
    public int save(OperatorSaveRequest request) {
        OperatorDO data = Converter.toOperatorDO(request);
        if (data.getId() == null) {
            data.setAlgoPath("");
            return operatorDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return operatorDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        OperatorDO data = new OperatorDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return operatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public OperatorVO getConfig() {
        List<SystemConfigDO> result = configServiceImpl.getByKeys(Lists.newArrayList(
                ConfigConstants.ALGO_CODE_TEMPLATE,
                ConfigConstants.INPUT_PARAM_TEMPLATE,
                ConfigConstants.OUTPUT_PARAM_TEMPLATE));
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        OperatorVO operatorVO = new OperatorVO();
        result.forEach(configVO -> {
            if (ConfigConstants.ALGO_CODE_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setAlgoCode(configVO.getConfigValue());
            } else if (ConfigConstants.INPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setInputParam(configVO.getConfigValue());
            } else if (ConfigConstants.OUTPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setOutputParam(configVO.getConfigValue());
            }
        });
        return operatorVO;
    }

    @Override
    public int publishApi(IdRequest request) {
        OperatorVO operatorVO = get(request);
        if (operatorVO == null) {
            throw new RuntimeException("发布Api失败，算子不存在！");
        }
        ApiDO apiDO = apiServiceImpl.getByType(AlgoTypeEnum.OPERATOR.name(), operatorVO.getId());
        if (apiDO == null) {
            apiDO = new ApiDO();
            apiDO.setApiCode(UuidUtil.getUuid());
        }
        apiDO.setApiName(operatorVO.getOpName() + "服务");
        apiDO.setApiType(AlgoTypeEnum.OPERATOR.name());
        apiDO.setTypeId(operatorVO.getId());
        apiDO.setApiUrl("");
        apiDO.setInputParam("");
        apiDO.setOutputParam("");
        apiDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        apiDO.setCallbackUrl("");
        apiDO.setStatus(ApiStatusEnum.PUBLISHING.name());
        apiServiceImpl.save(apiDO);
        String apiUrl = pythonOperatorServiceImpl.createApi(operatorVO, apiDO);
        apiDO.setApiUrl(apiUrl);
        apiDO.setStatus(ApiStatusEnum.PUBLISHED.name());
        return apiServiceImpl.save(apiDO);
    }

}
