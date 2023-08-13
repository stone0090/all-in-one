package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/06/22
 */
@Data
public class OperatorVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String opCode;
    private String opName;
    private String opStatus;
    private String opStatusName;
    private String programmingLanguage;
    private String algorithmCode;
    private String algorithmPath;
    private String inputParam;
    private String outputParam;
    private String svcStatus;
    private String svcStatusName;
    private String svcUrl;
}