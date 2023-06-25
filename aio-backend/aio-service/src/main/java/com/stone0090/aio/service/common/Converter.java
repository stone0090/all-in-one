package com.stone0090.aio.service.common;

import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.*;
import com.stone0090.aio.dao.mybatis.entity.*;
import org.springframework.beans.BeanUtils;

public class Converter {

    public static UserBriefVO toBriefVO(UserDO param) {
        UserBriefVO result = new UserBriefVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static UserDetailVO toDetailVO(UserDO param) {
        UserDetailVO result = new UserDetailVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static UserDO toUserDO(UserSaveRequest param) {
        UserDO result = new UserDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static RoleVO toRoleVO(RoleDO param) {
        RoleVO result = new RoleVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static RoleDO toRoleDO(RoleSaveRequest param) {
        RoleDO result = new RoleDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static PermissionVO toPermissionVO(PermissionDO param) {
        PermissionVO result = new PermissionVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static PermissionDO toPermissionDO(PermissionSaveRequest param) {
        PermissionDO result = new PermissionDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }
    public static ConfigVO toSystemConfigVO(SystemConfigDO param) {
        ConfigVO result = new ConfigVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static SystemConfigDO toSystemConfigDO(ConfigSaveRequest param) {
        SystemConfigDO result = new SystemConfigDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }


    public static OperatorVO toOperatorVO(OperatorDO param) {
        OperatorVO result = new OperatorVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static OperatorDO toOperatorDO(OperatorSaveRequest param) {
        OperatorDO result = new OperatorDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static ApiDO toApiDO(ApiSaveRequest param) {
        ApiDO result = new ApiDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static ApiVO toApiVO(ApiDO param) {
        ApiVO result = new ApiVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }
}
