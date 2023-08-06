package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.response.OperatorAndGroupVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author stone
 * @date 2023/06/22
 */
@Validated
public interface DagService {

    List<OperatorAndGroupVO> listOpGroups();
}
