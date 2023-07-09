package com.stone0090.aio.service.core;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.ApiVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/06/22
 */
@Validated
public interface ApiService {

    PageResult<ApiVO> list(@NotNull(message = "查询条件不能为空") @Valid ApiQueryRequest queryRequest,
                           @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    ApiVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int save(@NotNull(message = "入参不能为空") @Valid ApiSaveRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int publishApi(IdRequest request);

    String invokeApi(ApiRequest request);

}
