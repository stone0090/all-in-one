package com.stone0090.aio.service.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.RoleSaveRequest;
import com.stone0090.aio.api.request.SystemConfigQueryRequest;
import com.stone0090.aio.api.request.SystemConfigSaveRequest;
import com.stone0090.aio.api.request.IdentifierRequest;
import com.stone0090.aio.api.response.SystemConfigVO;
import com.stone0090.aio.dao.mybatis.entity.RoleDO;
import com.stone0090.aio.dao.mybatis.entity.RoleDOExample;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDOExample;
import com.stone0090.aio.dao.mybatis.mapper.SystemConfigDOMapper;
import com.stone0090.aio.service.SystemConfigService;
import com.stone0090.aio.service.converter.CommonConverter;
import com.stone0090.aio.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author stone
 * @date 2023/06/17
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigDOMapper systemConfigDOMapper;

    @Override
    public PageResult<SystemConfigVO> listConfigs(SystemConfigQueryRequest queryRequest, PageRequest pageRequest) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        SystemConfigDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getConfigKey())) {
            criteria.andConfigKeyLike("%" + queryRequest.getConfigKey() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<SystemConfigDO> result = systemConfigDOMapper.selectByExample(example);
        PageResult<SystemConfigVO> pageResult = PageResult.buildPageResult(result);
        return pageResult;
    }

    @Override
    public SystemConfigVO getConfig(IdentifierRequest request) {
        SystemConfigDOExample example = new SystemConfigDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<SystemConfigDO> result = systemConfigDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : CommonConverter.toSystemConfigVO(result.get(0));
    }

    @Override
    public int saveConfig(SystemConfigSaveRequest request) {
        SystemConfigDO configDO = CommonConverter.toSystemConfigDO(request);
        if (configDO.getId() == null) {
            return systemConfigDOMapper.insertSelective(configDO);
        } else {
            configDO.setGmtModified(new Date());
            return systemConfigDOMapper.updateByPrimaryKeySelective(configDO);
        }
    }

    @Override
    public int removeConfig(IdentifierRequest request) {
        SystemConfigDO configDO = new SystemConfigDO();
        configDO.setId(request.getId());
        configDO.setIsDeleted((int)System.currentTimeMillis());
        configDO.setGmtModified(new Date());
        return systemConfigDOMapper.updateByPrimaryKeySelective(configDO);
    }

}
