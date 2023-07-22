package com.stone0090.aio.service.model.web.protocal;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestResult implements Serializable {

    private Boolean success;
    private Object data;
    private String message;
    //    private String errorCode;
    //    private Integer showType;
    //    private String traceId;
    //    private String host;

    public RestResult(Boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static RestResult success() {
        return success(null);
    }

    public static RestResult success(Object data) {
        return new RestResult(true, data, null);
    }

    public static RestResult failure(String message) {
        return new RestResult(false, null, message);
    }

}
