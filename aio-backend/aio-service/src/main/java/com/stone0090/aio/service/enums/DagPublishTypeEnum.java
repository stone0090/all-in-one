package com.stone0090.aio.service.enums;

public enum DagPublishTypeEnum {
    SECOND_SCHEDULING("秒级调度"),
    ONLINE_SERVICE("在线服务"),
    ;

    private String desc;

    DagPublishTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(String code) {
        for (DagPublishTypeEnum opStatusEnum : DagPublishTypeEnum.values()) {
            if (opStatusEnum.name().equals(code)) {
                return opStatusEnum.getDesc();
            }
        }
        return null;
    }

}
