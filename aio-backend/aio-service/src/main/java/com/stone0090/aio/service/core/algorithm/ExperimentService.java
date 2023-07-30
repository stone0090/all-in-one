package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.ExperimentQueryRequest;
import com.stone0090.aio.service.model.web.request.ExperimentSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.ExperimentSaveDetailRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ExperimentBriefVO;
import com.stone0090.aio.service.model.web.response.ExperimentDetailVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/07/30
 */
@Validated
public interface ExperimentService {

    PageResult<ExperimentBriefVO> list(@NotNull(message = "查询条件不能为空") @Valid ExperimentQueryRequest queryRequest,
                                       @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    ExperimentDetailVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int saveBrief(@NotNull(message = "入参不能为空") @Valid ExperimentSaveBriefRequest request);

    int saveDetail(@NotNull(message = "入参不能为空") @Valid ExperimentSaveDetailRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);

}
