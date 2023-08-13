package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SvcRequest extends IdRequest implements Serializable{

    private String svcType;

}
