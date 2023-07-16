package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/06/23
 */
@Data
public class ApiVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String apiCode;
    private String apiName;
    private String apiType;
    private Integer typeId;
    private String apiUrl;
    private String inputParam;
    private String outputParam;
    private String invokeType;
    private String callbackUrl;
    private String status;
}