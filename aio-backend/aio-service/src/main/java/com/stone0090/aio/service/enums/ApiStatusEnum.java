package com.stone0090.aio.service.enums;

public enum ApiStatusEnum {
    UNPUBLISH("未发布"),
    PUBLISHING("发布中"),
    PUBLISHED("已发布"),
    ;

    private String desc;

    ApiStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
