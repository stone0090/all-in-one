package com.stone0090.aio.service.core.system;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.RoleQueryRequest;
import com.stone0090.aio.service.model.web.request.save.RoleSaveRequest;
import com.stone0090.aio.service.model.web.response.RoleVO;
import org.springframework.validation.annotation.Validated;

/**
 * @author stone
 * @date 2021/08/03
 */
@Validated
public interface RoleService {

    PageResult<RoleVO> list(@NotNull(message = "查询条件不能为空") @Valid RoleQueryRequest queryRequest,
                            @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    RoleVO get(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    int save(@NotNull(message = "入参不能为空") @Valid RoleSaveRequest request);

    int remove(@NotNull(message = "入参不能为空") @Valid IdRequest request);

    List<RoleVO> listByUsername(@NotNull(message = "入参不能为空") @Valid String username);
}
