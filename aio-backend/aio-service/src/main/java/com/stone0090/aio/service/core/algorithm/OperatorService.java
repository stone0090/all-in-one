package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.OperatorQueryRequest;
import com.stone0090.aio.service.model.web.request.OperatorSaveRequest;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/06/22
 */
@Validated
public interface OperatorService {

    PageResult<OperatorVO> list(@NotNull(message = "查询条件不能为空") @Valid OperatorQueryRequest queryRequest,
                                @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    OperatorVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int save(@NotNull(message = "入参不能为空") @Valid OperatorSaveRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    OperatorVO getDefaultConfig();

}
