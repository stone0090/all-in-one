package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class OperatorQueryRequest implements Serializable {
    /**
     * 算子标识模糊查询
     */
    private String opCode;
    /**
     * 算子名称糊查询
     */
    private String opName;
}
