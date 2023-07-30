package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class OperatorSaveRequest {

    private Integer id;

    @NotNull(message = "算子标识不能为空")
    @Size(min = 3, max = 50, message = "算子标识必须介于3到50位字符之间")
    @Pattern(regexp = "^\\w+$", message = "算子标识只能包含英文字母、数字、下划线")
    private String opCode;

    @NotNull(message = "算子名称不能为空")
    @Size(min = 1, max = 50, message = "算子名称必须介于1到50位字符之间")
    private String opName;

    @NotNull(message = "编程语言不能为空")
    @Size(max = 50, message = "编程语言必须小于50位字符")
    private String algoLanguage;

    @NotNull(message = "算子代码不能为空")
    @Size(max = 20000, message = "算子代码必须小于20000位字符")
    private String algoCode;

    @NotNull(message = "算子入参不能为空")
    @Size(max = 4000, message = "算子入参必须小于4000位字符")
    private String inputParam;

    @NotNull(message = "算子出参不能为空")
    @Size(max = 4000, message = "算子出参必须小于4000位字符")
    private String outputParam;

}