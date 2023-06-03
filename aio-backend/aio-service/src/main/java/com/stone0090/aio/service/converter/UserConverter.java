package com.stone0090.aio.service.converter;


import com.stone0090.aio.api.request.PermissionSaveRequest;
import com.stone0090.aio.api.request.RoleSaveRequest;
import com.stone0090.aio.api.request.UserSaveRequest;
import com.stone0090.aio.api.response.PermissionVO;
import com.stone0090.aio.api.response.RoleVO;
import com.stone0090.aio.api.response.UserBriefVO;
import com.stone0090.aio.api.response.UserDetailVO;
import com.stone0090.aio.dao.mybatis.entity.PermissionDO;
import com.stone0090.aio.dao.mybatis.entity.RoleDO;
import com.stone0090.aio.dao.mybatis.entity.UserDO;
import com.stone0090.aio.dao.mybatis.entity.UserRoleRelationDO;

public class UserConverter {

    public static UserBriefVO toBriefVO(UserDO userDO) {
        UserBriefVO userBriefVO = new UserBriefVO();
        userBriefVO.setId(userDO.getId());
        userBriefVO.setGmtModified(userDO.getGmtModified());
        userBriefVO.setUsername(userDO.getUsername());
        userBriefVO.setNickname(userDO.getNickname());
        userBriefVO.setPassword(userDO.getPassword());
        return userBriefVO;
    }

    public static UserDetailVO toDetailVO(UserDO userDO) {
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setId(userDO.getId());
        userDetailVO.setGmtModified(userDO.getGmtModified());
        userDetailVO.setUsername(userDO.getUsername());
        userDetailVO.setNickname(userDO.getNickname());
        userDetailVO.setPassword(userDO.getPassword());
        userDetailVO.setAvatar(userDO.getAvatar());
        userDetailVO.setResume(userDO.getResume());
        userDetailVO.setPhone(userDO.getPhone());
        userDetailVO.setEmail(userDO.getEmail());
        return userDetailVO;
    }

    public static UserDO toUserDO(UserSaveRequest request) {
        UserDO userDO = new UserDO();
        userDO.setId(request.getId());
        userDO.setUsername(request.getUsername());
        userDO.setPassword(request.getPassword());
        userDO.setNickname(request.getNickname());
        userDO.setResume(request.getResume());
        userDO.setPhone(request.getPhone());
        userDO.setEmail(request.getEmail());
        return userDO;
    }

    public static RoleVO toRoleVO(RoleDO roleDO) {
        RoleVO roleVO = new RoleVO();
        roleVO.setId(roleDO.getId());
        roleVO.setGmtModified(roleDO.getGmtModified());
        roleVO.setRoleCode(roleDO.getRoleCode());
        roleVO.setRoleName(roleDO.getRoleName());
        return roleVO;
    }

    public static RoleDO toRoleDO(RoleSaveRequest request) {
        RoleDO roleDO = new RoleDO();
        roleDO.setId(request.getId());
        roleDO.setRoleCode(request.getRoleCode());
        roleDO.setRoleName(request.getRoleName());
        return roleDO;
    }

    public static PermissionVO toPermissionVO(PermissionDO permissionDO) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(permissionDO.getId());
        permissionVO.setGmtModified(permissionDO.getGmtModified());
        permissionVO.setPermissionCode(permissionDO.getPermissionCode());
        permissionVO.setPermissionName(permissionDO.getPermissionName());
        permissionVO.setPermissionUrl(permissionDO.getPermissionUrl());
        return permissionVO;
    }

    public static PermissionDO toPermissionDO(PermissionSaveRequest request) {
        PermissionDO permissionDO = new PermissionDO();
        permissionDO.setId(request.getId());
        permissionDO.setPermissionCode(request.getPermissionCode());
        permissionDO.setPermissionName(request.getPermissionName());
        permissionDO.setPermissionUrl(request.getPermissionUrl());
        return permissionDO;
    }
}
