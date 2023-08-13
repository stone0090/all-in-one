package com.stone0090.aio.service.core.system.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.stone0090.aio.dao.mybatis.entity.RoleDO;
import com.stone0090.aio.dao.mybatis.entity.RoleDOExample;
import com.stone0090.aio.dao.mybatis.entity.UserRoleRelationDO;
import com.stone0090.aio.dao.mybatis.entity.UserRoleRelationDOExample;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.RoleQueryRequest;
import com.stone0090.aio.service.model.web.request.save.RoleSaveRequest;
import com.stone0090.aio.service.model.web.response.PermissionVO;
import com.stone0090.aio.service.model.web.response.RoleVO;
import com.stone0090.aio.dao.mybatis.mapper.RoleDOMapper;
import com.stone0090.aio.dao.mybatis.mapper.UserRoleRelationDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.github.pagehelper.PageHelper;
import com.stone0090.aio.service.core.system.PermissionService;
import com.stone0090.aio.service.core.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author stone
 * @date 2021/08/03
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDOMapper roleDOMapper;
    @Autowired
    private UserRoleRelationDOMapper userRoleRelationDOMapper;
    @Autowired
    private PermissionService permissionService;

    @Override
    public PageResult<RoleVO> list(RoleQueryRequest queryRequest, PageRequest pageRequest) {
        RoleDOExample example = new RoleDOExample();
        RoleDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getRoleCode())) {
            criteria.andRoleCodeLike("%" + queryRequest.getRoleCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getRoleName())) {
            criteria.andRoleNameLike("%" + queryRequest.getRoleName() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<RoleDO> result = roleDOMapper.selectByExample(example);
        PageResult<RoleVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(listRolePermissions(result));
        return pageResult;
    }

    @Override
    public RoleVO get(IdRequest request) {
        RoleDOExample example = new RoleDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<RoleDO> result = roleDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toRoleVO(result.get(0));
    }

    @Override
    public int save(RoleSaveRequest request) {
        RoleDO data = Converter.toRoleDO(request);
        if (data.getId() == null) {
            return roleDOMapper.insertSelective(data);
        } else {
            data.setRoleCode(null);
            data.setGmtModified(new Date());
            return roleDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        RoleDO data = new RoleDO();
        data.setId(request.getId());
        data.setIsDeleted((int)System.currentTimeMillis());
        data.setGmtModified(new Date());
        return roleDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public List<RoleVO> listByUsername(String username) {
        UserRoleRelationDOExample example = new UserRoleRelationDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andUsernameEqualTo(username);
        List<UserRoleRelationDO> userRoleRelationDOList = userRoleRelationDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userRoleRelationDOList)) {
            return Collections.emptyList();
        }
        List<String> roleCodeList = userRoleRelationDOList.stream().map(UserRoleRelationDO::getRoleCode)
            .collect(Collectors.toList());
        RoleDOExample roleDOExample = new RoleDOExample();
        roleDOExample.createCriteria().andIsDeletedEqualTo(0).andRoleCodeIn(roleCodeList);
        List<RoleDO> roleDOList = roleDOMapper.selectByExample(roleDOExample);
        if (CollectionUtils.isEmpty(roleDOList)) {
            return Collections.emptyList();
        }
        return listRolePermissions(roleDOList);
    }

    private List<RoleVO> listRolePermissions(List<RoleDO> roleDOList) {
        List<String> roleCodeList = roleDOList.stream().map(RoleDO::getRoleCode).collect(Collectors.toList());
        Map<String, List<PermissionVO>> roleCodePermissionVOMap = permissionService.listByRoleCodes(
            roleCodeList);
        if (CollectionUtils.isEmpty(roleCodePermissionVOMap)) {
            return roleDOList.stream().map(Converter::toRoleVO).collect(Collectors.toList());
        }
        return roleDOList.stream().map(roleDO -> {
            RoleVO roleVO = Converter.toRoleVO(roleDO);
            if (!CollectionUtils.isEmpty(roleCodePermissionVOMap)) {
                roleVO.setPermissions(roleCodePermissionVOMap.get(roleDO.getRoleCode()));
            }
            return roleVO;
        }).collect(Collectors.toList());
    }

}
