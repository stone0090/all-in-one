package com.stone0090.aio.service.model.web.request.save;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class DagSaveBriefRequest implements Serializable {

    private Integer id;

    @NotNull(message = "Dag名称不能为空")
    @Size(min = 3, max = 20, message = "Dag名称必须介于3到20位字符之间")
    private String dagName;

    @Size(max = 200, message = "Dag描述必须小于200位字符")
    private String dagDesc;

}
