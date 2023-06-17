package com.stone0090.aio.api.response;

import lombok.Data;

import java.util.Date;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class ConfigVO {
    private Integer id;
    private Date gmtModified;
    private String configKey;
    private String configValue;
}