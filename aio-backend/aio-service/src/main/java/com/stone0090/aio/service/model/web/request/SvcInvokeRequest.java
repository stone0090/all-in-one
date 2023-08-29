package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SvcInvokeRequest extends IdRequest implements Serializable{

    private String inputParam;

}
