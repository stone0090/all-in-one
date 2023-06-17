package com.stone0090.aio.service.converter;


import com.stone0090.aio.api.request.ConfigSaveRequest;
import com.stone0090.aio.api.response.ConfigVO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;

public class CommonConverter {

    public static ConfigVO toSystemConfigVO(SystemConfigDO param) {
        ConfigVO result = new ConfigVO();
        result.setId(param.getId());
        result.setGmtModified(param.getGmtModified());
        result.setConfigKey(param.getConfigKey());
        result.setConfigValue(param.getConfigValue());
        return result;
    }

    public static SystemConfigDO toSystemConfigDO(ConfigSaveRequest param) {
        SystemConfigDO result = new SystemConfigDO();
        result.setId(param.getId());
        result.setConfigKey(param.getConfigKey());
        result.setConfigValue(param.getConfigValue());
        return result;
    }

}
