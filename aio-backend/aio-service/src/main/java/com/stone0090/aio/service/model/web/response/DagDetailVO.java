package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2023/07/30
 */
@Data
public class DagDetailVO extends DagBriefVO implements Serializable {
    private String dagNodes;
    private String publishType;
    private String publishConfig;
}