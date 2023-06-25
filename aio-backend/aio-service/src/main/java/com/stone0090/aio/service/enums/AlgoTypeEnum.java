package com.stone0090.aio.service.enums;

public enum AlgoTypeEnum {

    /**
     * 1. 算子
     */
    OPERATOR(1, "算子"),
    /**
     * 2. 模型
     */
    MODEL(2, "模型"),
    /**
     * 3. 画布
     */
    EXPERIMENT(3, "画布");

    private final int code;
    private final String desc;

    AlgoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AlgoTypeEnum getByCode(int code) {
        for (AlgoTypeEnum value : AlgoTypeEnum.values()) {
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
