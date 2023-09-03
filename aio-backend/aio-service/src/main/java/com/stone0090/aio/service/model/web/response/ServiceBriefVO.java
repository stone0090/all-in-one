package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/06/23
 */
@Data
public class ServiceBriefVO implements Serializable {
    private String inputParam;
    private String outputParam;
    private String svcStatus;
    private String svcStatusName;
    private String svcUrl;
}