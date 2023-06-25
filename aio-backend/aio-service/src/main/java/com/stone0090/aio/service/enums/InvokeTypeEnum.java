package com.stone0090.aio.service.enums;

public enum InvokeTypeEnum {
    SYNC("同步"),
    ASYNC("异步");

    private String desc;

    InvokeTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
