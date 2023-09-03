package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DagPort {
    private String id;
    private String tooltip;
    private String group;   // top/bottom/right/left
    private String type;    // input/output
    private String opParamCode;
    private String opParamName;
    private String opParamType;
    private Boolean opParamRequired;
    private Object opParamDefaultValue;
    private String nodeId;
    private String nodeLabel;
}