package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.DagQueryRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveDetailRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.DagBriefVO;
import com.stone0090.aio.service.model.web.response.DagDetailVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author stone
 * @date 2023/07/30
 */
@Validated
public interface DagService {

    PageResult<DagBriefVO> list(@NotNull(message = "查询条件不能为空") @Valid DagQueryRequest queryRequest,
                                @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    DagDetailVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int saveBrief(@NotNull(message = "入参不能为空") @Valid DagSaveBriefRequest request);

    int saveDetail(@NotNull(message = "入参不能为空") @Valid DagSaveDetailRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);



}
