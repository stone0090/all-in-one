package com.stone0090.aio.manager.utils;

public class UuidUtil {
    public static String getUuid() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
