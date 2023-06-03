package com.stone0090.aio.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.IdentifierRequest;
import com.stone0090.aio.api.request.PermissionQueryRequest;
import com.stone0090.aio.api.request.PermissionSaveRequest;
import com.stone0090.aio.api.response.PermissionVO;
import org.springframework.validation.annotation.Validated;

/**
 * @author stone
 * @date 2021/08/03
 */
@Validated
public interface PermissionService {

    PageResult<PermissionVO> listPermissions(@NotNull(message = "查询条件不能为空") @Valid PermissionQueryRequest queryRequest,
                                             @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    PermissionVO getPermission(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);

    int savePermission(@NotNull(message = "入参不能为空") @Valid PermissionSaveRequest request);

    int removePermission(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);

    Map<String, List<PermissionVO>> listPermissionsByRoleCodes(
        @NotNull(message = "入参不能为空") @Valid List<String> roleCodeList);
}
