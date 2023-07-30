package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stone
 * @date 2023/07/30
 */
@Data
public class ExperimentDetailVO extends ExperimentBriefVO implements Serializable {

    private String nodeDag;

    private String publishType;

    private String publishConfig;

}