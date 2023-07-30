package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/07/30
 */
@Data
public class ExperimentBriefVO implements Serializable {

    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private String exName;

    private String exDesc;

    private String exStatus;

    private String exStatusName;

}