package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class ConfigQueryRequest implements Serializable {
    /**
     * 配置项模糊查询
     */
    private String configKey;
    /**
     * 配置项模糊查询
     */
    private String configValue;
}
