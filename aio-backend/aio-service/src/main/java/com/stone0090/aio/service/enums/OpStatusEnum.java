package com.stone0090.aio.service.enums;

public enum OpStatusEnum {
    INIT("开发中"),
    PUBLISHED("已上架"),
    DEPRECATED("已废弃"),
    ;

    private String desc;

    OpStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (OpStatusEnum opStatusEnum : OpStatusEnum.values()) {
            if (opStatusEnum.name().equals(code)) {
                return opStatusEnum.getDesc();
            }
        }
        return null;
    }

}
