package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DeployNode {
    private String nodeId;
    private String[] previous;
    private String[] next;
    private Boolean needDeploy;
    private String language;
    private String algoCode;
}
