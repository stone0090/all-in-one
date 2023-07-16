package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/06/17
 */
@Data
public class ConfigVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String configKey;
    private String configValue;
}