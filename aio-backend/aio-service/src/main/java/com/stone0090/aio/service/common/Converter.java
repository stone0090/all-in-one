package com.stone0090.aio.service.common;

import com.stone0090.aio.dao.mybatis.entity.*;
import com.stone0090.aio.service.enums.ApiStatusEnum;
import com.stone0090.aio.service.enums.ExStatusEnum;
import com.stone0090.aio.service.enums.OpStatusEnum;
import com.stone0090.aio.service.model.web.request.*;
import com.stone0090.aio.service.model.web.response.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;

import java.util.Map;

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
        result.setOpStatusName(OpStatusEnum.getDescByCode(param.getOpStatus()));
        return result;
    }

    public static OperatorVO toOperatorVO(OperatorDO param, Map<Integer, ApiDO> apiMap) {
        OperatorVO result = new OperatorVO();
        BeanUtils.copyProperties(param, result);
        result.setOpStatusName(OpStatusEnum.getDescByCode(param.getOpStatus()));
        if (MapUtils.isNotEmpty(apiMap) && apiMap.containsKey(param.getId())) {
            result.setApiStatus(apiMap.get(param.getId()).getApiStatus());
            result.setApiStatusName(ApiStatusEnum.getDescByCode(apiMap.get(param.getId()).getApiStatus()));
            result.setApiUrl("/aio/api/invoke?serviceId=" + apiMap.get(param.getId()).getApiUuid());
        } else {
            result.setApiUrl("/aio/api/invoke?serviceId=");
        }
        return result;
    }

    public static OperatorDO toOperatorDO(OperatorSaveRequest param) {
        OperatorDO result = new OperatorDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static ExperimentBriefVO toExperimentBriefVO(ExperimentDO param) {
        ExperimentBriefVO result = new ExperimentBriefVO();
        BeanUtils.copyProperties(param, result);
        result.setExStatusName(ExStatusEnum.getDescByCode(param.getExStatus()));
        return result;
    }

    public static ExperimentDetailVO toExperimentDetailVO(ExperimentDO param) {
        ExperimentDetailVO result = new ExperimentDetailVO();
        BeanUtils.copyProperties(param, result);
        result.setExStatusName(ExStatusEnum.getDescByCode(param.getExStatus()));
        return result;
    }


    public static ExperimentDO toExperimentDO(ExperimentSaveBriefRequest param) {
        ExperimentDO result = new ExperimentDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static ExperimentDO toExperimentDO(ExperimentSaveDetailRequest param) {
        ExperimentDO result = new ExperimentDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }


}
