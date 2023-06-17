package com.stone0090.aio.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.ConfigQueryRequest;
import com.stone0090.aio.api.request.ConfigSaveRequest;
import com.stone0090.aio.api.request.IdentifierRequest;
import com.stone0090.aio.api.response.ConfigVO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDOExample;
import com.stone0090.aio.dao.mybatis.mapper.SystemConfigDOMapper;
import com.stone0090.aio.service.system.ConfigService;
import com.stone0090.aio.service.converter.CommonConverter;
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
    private SystemConfigDOMapper systemConfigDOMapper;

    @Override
    public PageResult<ConfigVO> list(ConfigQueryRequest queryRequest, PageRequest pageRequest) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        SystemConfigDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getConfigKey())) {
            criteria.andConfigKeyLike("%" + queryRequest.getConfigKey() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getConfigValue())) {
            criteria.andConfigValueLike("%" + queryRequest.getConfigValue() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<SystemConfigDO> result = systemConfigDOMapper.selectByExample(example);
        PageResult<ConfigVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(CommonConverter::toSystemConfigVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public ConfigVO get(IdentifierRequest request) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<SystemConfigDO> result = systemConfigDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : CommonConverter.toSystemConfigVO(result.get(0));
    }

    @Override
    public int save(ConfigSaveRequest request) {
        SystemConfigDO configDO = CommonConverter.toSystemConfigDO(request);
        if (configDO.getId() == null) {
            return systemConfigDOMapper.insertSelective(configDO);
        } else {
            configDO.setGmtModified(new Date());
            return systemConfigDOMapper.updateByPrimaryKeySelective(configDO);
        }
    }

    @Override
    public int remove(IdentifierRequest request) {
        SystemConfigDO configDO = new SystemConfigDO();
        configDO.setId(request.getId());
        configDO.setIsDeleted((int)System.currentTimeMillis());
        configDO.setGmtModified(new Date());
        return systemConfigDOMapper.updateByPrimaryKeySelective(configDO);
    }

}
