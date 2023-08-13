package com.stone0090.aio.service.enums;

public enum AlgorithmTypeEnum {

    /**
     * 1. 算子
     */
    OPERATOR(1, "算子"),
    /**
     * 2. Dag
     */
    DAG(2, "Dag"),
    /**
     * 3. 模型
     */
    MODEL(3, "模型"),
    
    ;

    private final int code;
    private final String desc;

    AlgorithmTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AlgorithmTypeEnum getByCode(int code) {
        for (AlgorithmTypeEnum value : AlgorithmTypeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
