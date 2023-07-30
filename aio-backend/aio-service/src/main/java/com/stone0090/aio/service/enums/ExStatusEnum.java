package com.stone0090.aio.service.enums;

public enum ExStatusEnum {
    INIT("开发中"),
    PUBLISHED("已部署"),
    ;

    private String desc;

    ExStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (ExStatusEnum opStatusEnum : ExStatusEnum.values()) {
            if (opStatusEnum.name().equals(code)) {
                return opStatusEnum.getDesc();
            }
        }
        return null;
    }

}
