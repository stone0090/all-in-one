package com.stone0090.aio.service.enums;

public enum ApiStatusEnum {
    OFFLINE("离线"),
    ONLINE("在线"),
    ;

    private String desc;

    ApiStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
