package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class IdRequest implements Serializable {

    @NotNull(message = "id不能为空")
    private Integer id;

}
