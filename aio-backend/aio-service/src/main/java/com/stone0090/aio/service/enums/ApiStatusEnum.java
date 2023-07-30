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

    public static String getDescByCode(String code) {
        for (ApiStatusEnum apiStatusEnum : ApiStatusEnum.values()) {
            if (apiStatusEnum.name().equals(code)) {
                return apiStatusEnum.getDesc();
            }
        }
        return null;
    }

}
