package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/06/23
 */
@Data
public class ServiceVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String svcCode;
    private String svcName;
    private String svcType;
    private Integer bizId;
    private String svcUrl;
    private String inputParam;
    private String outputParam;
    private String invokeType;
    private String callbackUrl;
    private String svcStatus;
}