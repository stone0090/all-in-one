package com.stone0090.aio.service;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.SystemConfigQueryRequest;
import com.stone0090.aio.api.request.SystemConfigSaveRequest;
import com.stone0090.aio.api.request.IdentifierRequest;
import com.stone0090.aio.api.response.SystemConfigVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/06/17
 */
@Validated
public interface SystemConfigService {

    PageResult<SystemConfigVO> listConfigs(@NotNull(message = "查询条件不能为空") @Valid SystemConfigQueryRequest queryRequest,
                                           @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    SystemConfigVO getConfig(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);

    int saveConfig(@NotNull(message = "入参不能为空") @Valid SystemConfigSaveRequest request);

    int removeConfig(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);
}
