package com.stone0090.aio.service.enums;

public enum ScheduleTypeEnum {

    cron("Cron调度"),

    interval("定时调度"),

    none("不调度");

    private String desc;

    ScheduleTypeEnum(String desc) {
        this.desc = desc;
    }
}
