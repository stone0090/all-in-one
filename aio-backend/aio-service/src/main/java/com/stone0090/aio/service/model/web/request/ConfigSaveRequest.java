package com.stone0090.aio.service.model.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class ConfigSaveRequest {

    private Integer id;

    @NotNull(message = "配置项不能为空")
    @Size(min = 3, max = 50, message = "配置项必须介于3到50位字符之间")
    @Pattern(regexp = "^\\w+$", message = "配置项只能包含英文字母、数字、下划线")
    private String configKey;

    @NotNull(message = "配置值不能为空")
    @Size(min = 1, max = 4000, message = "配置值必须介于1到4000位字符之间")
    private String configValue;

}