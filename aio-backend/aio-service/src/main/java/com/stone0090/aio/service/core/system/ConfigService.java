package com.stone0090.aio.service.core.system;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.ConfigQueryRequest;
import com.stone0090.aio.service.model.web.request.save.ConfigSaveRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ConfigVO;
import com.stone0090.aio.service.model.web.response.OperatorVO;
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

    OperatorVO getOperatorDefaultConfig();
}
