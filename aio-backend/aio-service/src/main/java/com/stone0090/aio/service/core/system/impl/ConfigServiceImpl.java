package com.stone0090.aio.service.core.system.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.ConfigQueryRequest;
import com.stone0090.aio.service.model.web.request.ConfigSaveRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ConfigVO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDOExample;
import com.stone0090.aio.dao.mybatis.mapper.SystemConfigDOMapper;
import com.stone0090.aio.service.core.system.ConfigService;
import com.stone0090.aio.service.common.Converter;
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
        pageResult.setList(result.stream().map(Converter::toSystemConfigVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public ConfigVO get(IdRequest request) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<SystemConfigDO> result = systemConfigDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toSystemConfigVO(result.get(0));
    }

    @Override
    public int save(ConfigSaveRequest request) {
        SystemConfigDO data = Converter.toSystemConfigDO(request);
        if (data.getId() == null) {
            return systemConfigDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return systemConfigDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        SystemConfigDO data = new SystemConfigDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return systemConfigDOMapper.updateByPrimaryKeySelective(data);
    }

    public String getValueByKey(String key) {
        List<SystemConfigDO> result = getByKeys(Lists.newArrayList(key));
        return CollectionUtils.isEmpty(result) ? null : result.get(0).getConfigValue();
    }

    public List<SystemConfigDO> getByKeys(List<String> keys) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andConfigKeyIn(keys);
        return systemConfigDOMapper.selectByExample(example);
    }

}
