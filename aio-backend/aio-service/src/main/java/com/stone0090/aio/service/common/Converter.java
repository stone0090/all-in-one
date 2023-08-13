package com.stone0090.aio.service.common;

import com.stone0090.aio.dao.mybatis.entity.*;
import com.stone0090.aio.service.enums.SvcStatusEnum;
import com.stone0090.aio.service.enums.DagStatusEnum;
import com.stone0090.aio.service.enums.OpStatusEnum;
import com.stone0090.aio.service.model.web.request.save.*;
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

    public static ConfigVO toConfigVO(ConfigDO param) {
        ConfigVO result = new ConfigVO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static ConfigDO toConfigDO(ConfigSaveRequest param) {
        ConfigDO result = new ConfigDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static OperatorVO toOperatorVO(OperatorDO param) {
        OperatorVO result = new OperatorVO();
        BeanUtils.copyProperties(param, result);
        result.setOpStatusName(OpStatusEnum.getDescByCode(param.getOpStatus()));
        return result;
    }

    public static OperatorVO toOperatorVO(OperatorDO param, Map<Integer, ServiceDO> serviceMap) {
        OperatorVO result = new OperatorVO();
        BeanUtils.copyProperties(param, result);
        result.setOpStatusName(OpStatusEnum.getDescByCode(param.getOpStatus()));
        if (MapUtils.isNotEmpty(serviceMap) && serviceMap.containsKey(param.getId())) {
            result.setSvcStatus(serviceMap.get(param.getId()).getSvcStatus());
            result.setSvcStatusName(SvcStatusEnum.getDescByCode(serviceMap.get(param.getId()).getSvcStatus()));
            result.setSvcUrl("/aio/svc/invoke?serviceId=" + serviceMap.get(param.getId()).getSvcUuid());
        } else {
            result.setSvcUrl("/aio/svc/invoke?serviceId=");
        }
        return result;
    }

    public static OperatorDO toOperatorDO(OperatorSaveRequest param) {
        OperatorDO result = new OperatorDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static DagBriefVO toDagBriefVO(OperatorDagDO param) {
        DagBriefVO result = new DagBriefVO();
        BeanUtils.copyProperties(param, result);
        result.setDagStatusName(DagStatusEnum.getDescByCode(param.getDagStatus()));
        return result;
    }

    public static DagDetailVO toDagDetailVO(OperatorDagDO param) {
        DagDetailVO result = new DagDetailVO();
        BeanUtils.copyProperties(param, result);
        result.setDagStatusName(DagStatusEnum.getDescByCode(param.getDagStatus()));
        return result;
    }

    public static OperatorDagDO toOperatorDagDO(DagSaveBriefRequest param) {
        OperatorDagDO result = new OperatorDagDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }

    public static OperatorDagDO toOperatorDagDO(DagSaveDetailRequest param) {
        OperatorDagDO result = new OperatorDagDO();
        BeanUtils.copyProperties(param, result);
        return result;
    }


}
