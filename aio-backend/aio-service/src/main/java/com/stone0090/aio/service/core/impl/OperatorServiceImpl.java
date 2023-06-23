package com.stone0090.aio.service.core.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.ConfigVO;
import com.stone0090.aio.api.response.OperatorVO;
import com.stone0090.aio.dao.mybatis.entity.CoreOperatorDO;
import com.stone0090.aio.dao.mybatis.entity.CoreOperatorDOExample;
import com.stone0090.aio.dao.mybatis.mapper.CoreOperatorDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.OperatorService;
import com.stone0090.aio.service.system.ConfigService;
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
    private CoreOperatorDOMapper coreOperatorDOMapper;

    @Autowired
    private ConfigService configService;

    private static final String CORE_OPERATOR_PREFIX = "core_operator_";
    private static final String ALGO_CODE_TEMPLATE = "core_operator_algo_code_template";
    private static final String INPUT_PARAM_TEMPLATE = "core_operator_input_param_template";
    private static final String OUTPUT_PARAM_TEMPLATE = "core_operator_output_param_template";

    @Override
    public PageResult<OperatorVO> list(OperatorQueryRequest queryRequest, PageRequest pageRequest) {
        CoreOperatorDOExample example = new CoreOperatorDOExample();
        CoreOperatorDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getOpCode())) {
            criteria.andOpCodeLike("%" + queryRequest.getOpCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getOpName())) {
            criteria.andOpNameLike("%" + queryRequest.getOpName() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<CoreOperatorDO> result = coreOperatorDOMapper.selectByExample(example);
        PageResult<OperatorVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toOperatorVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public OperatorVO get(IdRequest request) {
        CoreOperatorDOExample example = new CoreOperatorDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<CoreOperatorDO> result = coreOperatorDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toOperatorVO(result.get(0));
    }

    @Override
    public int save(OperatorSaveRequest request) {
        CoreOperatorDO data = Converter.toCoreOperatorDO(request);
        if (data.getId() == null) {
            data.setAlgoPath("");
            return coreOperatorDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return coreOperatorDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        CoreOperatorDO data = new CoreOperatorDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return coreOperatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public OperatorVO getConfig() {
        ConfigQueryRequest request = new ConfigQueryRequest();
        request.setConfigKey(CORE_OPERATOR_PREFIX);
        PageResult<ConfigVO> result = configService.list(request, new PageRequest());
        if (result.getTotal() <= 0) {
            return null;
        }
        OperatorVO operatorVO = new OperatorVO();
        result.getList().forEach(configVO -> {
            if (ALGO_CODE_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setAlgoCode(configVO.getConfigValue());
            } else if (INPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setInputParam(configVO.getConfigValue());
            } else if (OUTPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setOutputParam(configVO.getConfigValue());
            }
        });
        return operatorVO;
    }

}
