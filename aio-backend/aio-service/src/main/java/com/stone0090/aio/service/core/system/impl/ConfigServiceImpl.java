package com.stone0090.aio.service.core.system.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.stone0090.aio.dao.mybatis.entity.ConfigDO;
import com.stone0090.aio.dao.mybatis.entity.ConfigDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ConfigDOMapper;
import com.stone0090.aio.service.common.ConfigConstants;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.ConfigQueryRequest;
import com.stone0090.aio.service.model.web.request.save.ConfigSaveRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ConfigVO;
import com.stone0090.aio.service.core.system.ConfigService;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stone
 * @date 2023/06/17
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDOMapper configDOMapper;

    @Override
    public PageResult<ConfigVO> list(ConfigQueryRequest queryRequest, PageRequest pageRequest) {
        ConfigDOExample example = new ConfigDOExample();
        ConfigDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getConfigKey())) {
            criteria.andConfigKeyLike("%" + queryRequest.getConfigKey() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getConfigValue())) {
            criteria.andConfigValueLike("%" + queryRequest.getConfigValue() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<ConfigDO> result = configDOMapper.selectByExample(example);
        PageResult<ConfigVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toConfigVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public ConfigVO get(IdRequest request) {
        ConfigDOExample example = new ConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<ConfigDO> result = configDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toConfigVO(result.get(0));
    }

    @Override
    public int save(ConfigSaveRequest request) {
        ConfigDO data = Converter.toConfigDO(request);
        if (data.getId() == null) {
            return configDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return configDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        ConfigDO data = new ConfigDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return configDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public OperatorVO getOperatorDefaultConfig() {
        List<ConfigDO> result = getByKeys(Lists.newArrayList(
                ConfigConstants.ALGO_CODE_TEMPLATE,
                ConfigConstants.INPUT_PARAM_TEMPLATE,
                ConfigConstants.OUTPUT_PARAM_TEMPLATE));
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        OperatorVO operatorVO = new OperatorVO();
        result.forEach(configVO -> {
            if (ConfigConstants.ALGO_CODE_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setAlgorithmCode(configVO.getConfigValue());
            } else if (ConfigConstants.INPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setInputParam(configVO.getConfigValue());
            } else if (ConfigConstants.OUTPUT_PARAM_TEMPLATE.equals(configVO.getConfigKey())) {
                operatorVO.setOutputParam(configVO.getConfigValue());
            }
        });
        return operatorVO;
    }

    public String getValueByKey(String key) {
        List<ConfigDO> result = getByKeys(Lists.newArrayList(key));
        return CollectionUtils.isEmpty(result) ? null : result.get(0).getConfigValue();
    }

    public List<ConfigDO> getByKeys(List<String> keys) {
        ConfigDOExample example = new ConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andConfigKeyIn(keys);
        return configDOMapper.selectByExample(example);
    }

}
