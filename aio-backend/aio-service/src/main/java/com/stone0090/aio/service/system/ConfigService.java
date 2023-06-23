package com.stone0090.aio.service.system;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.ConfigQueryRequest;
import com.stone0090.aio.api.request.ConfigSaveRequest;
import com.stone0090.aio.api.request.IdRequest;
import com.stone0090.aio.api.response.ConfigVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/06/17
 */
@Validated
public interface ConfigService {

    PageResult<ConfigVO> list(@NotNull(message = "查询条件不能为空") @Valid ConfigQueryRequest queryRequest,
                              @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    ConfigVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int save(@NotNull(message = "入参不能为空") @Valid ConfigSaveRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);
}
