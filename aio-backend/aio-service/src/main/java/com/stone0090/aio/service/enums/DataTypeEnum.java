package com.stone0090.aio.service.enums;

import java.util.Objects;

public enum DataTypeEnum {
    INTEGER("int", "整型"),
    DOUBLE("double", "浮点型"),
    STRING("string", "字符串"),
    BOOLEAN("boolean", "布尔型"),

    ;

    private final String code;
    private final String desc;

    DataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DataTypeEnum getByCode(String code) {
        for (DataTypeEnum value : DataTypeEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public static boolean checkType(String code, Object value) {
        DataTypeEnum dataTypeEnum = getByCode(code);
        if (dataTypeEnum != null) {
            switch (dataTypeEnum) {
                case INTEGER:
                    if (value instanceof Integer || value instanceof Long) {
                        return true;
                    }
                    break;
                case DOUBLE:
                    if (value instanceof Double) {
                        return true;
                    }
                    break;
                case STRING:
                    if (value instanceof String) {
                        return true;
                    }
                    break;
                case BOOLEAN:
                    if (value instanceof Boolean) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
