package com.stone0090.aio.service.user.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.IdentifierRequest;
import com.stone0090.aio.api.request.RoleQueryRequest;
import com.stone0090.aio.api.request.RoleSaveRequest;
import com.stone0090.aio.api.response.PermissionVO;
import com.stone0090.aio.api.response.RoleVO;
import com.stone0090.aio.dao.mybatis.entity.RoleDO;
import com.stone0090.aio.dao.mybatis.entity.RoleDOExample;
import com.stone0090.aio.dao.mybatis.entity.RoleDOExample.Criteria;
import com.stone0090.aio.dao.mybatis.entity.UserRoleRelationDO;
import com.stone0090.aio.dao.mybatis.entity.UserRoleRelationDOExample;
import com.stone0090.aio.dao.mybatis.mapper.RoleDOMapper;
import com.stone0090.aio.dao.mybatis.mapper.UserRoleRelationDOMapper;
import com.stone0090.aio.service.user.PermissionService;
import com.stone0090.aio.service.user.RoleService;
import com.stone0090.aio.service.converter.UserConverter;
import com.github.pagehelper.PageHelper;
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
        Criteria criteria = example.createCriteria();
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
    public RoleVO get(IdentifierRequest request) {
        RoleDOExample example = new RoleDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<RoleDO> result = roleDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : UserConverter.toRoleVO(result.get(0));
    }

    @Override
    public int save(RoleSaveRequest request) {
        RoleDO roleDO = UserConverter.toRoleDO(request);
        if (roleDO.getId() == null) {
            return roleDOMapper.insertSelective(roleDO);
        } else {
            roleDO.setRoleCode(null);
            roleDO.setGmtModified(new Date());
            return roleDOMapper.updateByPrimaryKeySelective(roleDO);
        }
    }

    @Override
    public int remove(IdentifierRequest request) {
        RoleDO roleDO = new RoleDO();
        roleDO.setId(request.getId());
        roleDO.setIsDeleted((int)System.currentTimeMillis());
        roleDO.setGmtModified(new Date());
        return roleDOMapper.updateByPrimaryKeySelective(roleDO);
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
            return roleDOList.stream().map(UserConverter::toRoleVO).collect(Collectors.toList());
        }
        return roleDOList.stream().map(roleDO -> {
            RoleVO roleVO = UserConverter.toRoleVO(roleDO);
            if (!CollectionUtils.isEmpty(roleCodePermissionVOMap)) {
                roleVO.setPermissions(roleCodePermissionVOMap.get(roleDO.getRoleCode()));
            }
            return roleVO;
        }).collect(Collectors.toList());
    }

}
