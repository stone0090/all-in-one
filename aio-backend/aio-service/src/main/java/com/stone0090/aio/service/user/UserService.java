package com.stone0090.aio.service.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.UserBriefVO;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.response.UserDetailVO;
import org.springframework.validation.annotation.Validated;

/**
 * @author stone
 * @date 2021/08/03
 */
@Validated
public interface UserService  {

    PageResult<UserBriefVO> list(@NotNull(message = "查询条件不能为空") @Valid UserQueryRequest queryRequest,
                                 @NotNull(message = "分页参数不能为空") @Valid PageRequest pageRequest);

    UserDetailVO getDetail(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);

    UserDetailVO getDetail(@NotNull(message = "入参不能为空") String username);

    /**
     * @return 返回主键id
     */
    int add(@NotNull(message = "入参不能为空") @Valid UserSaveRequest request);

    /**
     * @return 返回操作记录数
     */
    int edit(@NotNull(message = "入参不能为空") @Valid UserSaveRequest request);

    /**
     * @return 返回操作记录数
     */
    int remove(@NotNull(message = "入参不能为空") @Valid IdentifierRequest request);
}
