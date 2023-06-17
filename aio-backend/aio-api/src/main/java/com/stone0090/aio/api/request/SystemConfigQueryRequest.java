package com.stone0090.aio.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class SystemConfigQueryRequest implements Serializable {
    /**
     * 配置项模糊查询
     */
    private String configKey;
}
