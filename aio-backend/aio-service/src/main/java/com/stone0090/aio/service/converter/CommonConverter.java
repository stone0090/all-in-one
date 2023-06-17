package com.stone0090.aio.service.converter;


import com.stone0090.aio.api.request.SystemConfigSaveRequest;
import com.stone0090.aio.api.response.SystemConfigVO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;

public class CommonConverter {

    public static SystemConfigVO toSystemConfigVO(SystemConfigDO param) {
        SystemConfigVO result = new SystemConfigVO();
        result.setId(param.getId());
        result.setGmtModified(param.getGmtModified());
        result.setConfigKey(param.getConfigKey());
        result.setConfigValue(param.getConfigValue());
        return result;
    }

    public static SystemConfigDO toSystemConfigDO(SystemConfigSaveRequest param) {
        SystemConfigDO result = new SystemConfigDO();
        result.setId(param.getId());
        result.setConfigKey(param.getConfigKey());
        result.setConfigValue(param.getConfigValue());
        return result;
    }

}
