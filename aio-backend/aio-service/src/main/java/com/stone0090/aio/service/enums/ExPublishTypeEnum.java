package com.stone0090.aio.service.enums;

public enum ExPublishTypeEnum {
    SECOND_SCHEDULING("秒级调度"),
    API_SERVICE("API服务"),
    ;

    private String desc;

    ExPublishTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (ExPublishTypeEnum opStatusEnum : ExPublishTypeEnum.values()) {
            if (opStatusEnum.name().equals(code)) {
                return opStatusEnum.getDesc();
            }
        }
        return null;
    }

}
