package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class DagQueryRequest implements Serializable {
    /**
     * dag名称模糊查询
     */
    private String dagName;
    /**
     * dag状态精确查询
     */
    private String dagStatus;
}
