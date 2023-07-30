package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class ExperimentQueryRequest implements Serializable {
    /**
     * 画布名称模糊查询
     */
    private String exName;
    /**
     * 画布状态精确查询
     */
    private String exStatus;
}
