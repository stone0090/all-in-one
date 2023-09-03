package com.stone0090.aio.service.enums;

public enum AlgorithmTypeEnum {

    /**
     * 1. 算子
     */
    OPERATOR("算子"),
    /**
     * 2. Dag
     */
    DAG("Dag"),
    /**
     * 3. 模型
     */
    MODEL("模型"),

    ;

    private final String desc;

    AlgorithmTypeEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }
}
