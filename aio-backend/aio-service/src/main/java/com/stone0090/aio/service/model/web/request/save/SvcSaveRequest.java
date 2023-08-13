package com.stone0090.aio.service.model.web.request.save;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class SvcSaveRequest {

    private Integer id;

    @NotNull(message = "服务标识不能为空")
    @Size(min = 3, max = 50, message = "服务标识必须介于3到50位字符之间")
    @Pattern(regexp = "^\\w+$", message = "服务标识只能包含英文字母、数字、下划线")
    private String svcCode;

    @NotNull(message = "服务名称不能为空")
    @Size(min = 1, max = 20, message = "服务名称必须介于1到20位字符之间")
    private String svcName;

    @NotNull(message = "服务类型不能为空")
    @Size(min = 1, max = 20, message = "服务类型必须介于1到20位字符之间")
    private String svcType;

    @NotNull(message = "业务id不能为空")
    private Integer bizId;

    @NotNull(message = "服务地址不能为空")
    @Size(min = 1, max = 1000, message = "服务地址必须介于1到1000位字符之间")
    private String svcUrl;

    @NotNull(message = "输入参数不能为空")
    @Size(min = 1, max = 4000, message = "输入参数必须介于1到4000位字符之间")
    private String inputParam;

    @NotNull(message = "输出参数不能为空")
    @Size(min = 1, max = 4000, message = "输出参数必须介于1到4000位字符之间")
    private String outputParam;

    @NotNull(message = "调用类型不能为空")
    @Size(min = 1, max = 20, message = "调用类型必须介于1到20位字符之间")
    private String invokeType;

    @NotNull(message = "回调地址不能为空")
    @Size(min = 1, max = 1000, message = "回调地址必须介于1到1000位字符之间")
    private String callbackUrl;

    @NotNull(message = "状态不能为空")
    @Size(min = 1, max = 20, message = "状态必须介于1到20位字符之间")
    private String svcStatus;

}