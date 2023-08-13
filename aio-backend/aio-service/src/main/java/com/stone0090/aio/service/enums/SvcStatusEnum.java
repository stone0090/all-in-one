package com.stone0090.aio.service.enums;

public enum SvcStatusEnum {
    OFFLINE("离线"),
    ONLINE("在线"),
    ;

    private String desc;

    SvcStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (SvcStatusEnum svcStatusEnum : SvcStatusEnum.values()) {
            if (svcStatusEnum.name().equals(code)) {
                return svcStatusEnum.getDesc();
            }
        }
        return null;
    }

}
