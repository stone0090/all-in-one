package com.stone0090.aio.api.response;

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
    private String algoLanguage;
    private String algoCode;
    private String algoPath;
    private String inputParam;
    private String outputParam;
    private Integer isDisabled;
}