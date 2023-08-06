package com.stone0090.aio.service.model.web.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author stone
 * @date 2023/07/30
 */
@Data
public class OperatorAndGroupVO implements Serializable {

    private Integer groupId;

    private String groupName;

    private List<OperatorVO> operatorList;

}