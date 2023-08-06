package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.core.algorithm.DagService;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.ExperimentSaveBriefRequest;
import com.stone0090.aio.service.model.web.response.OperatorAndGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "DagController", tags = "算子编排")
@RequestMapping("/aio/dag")
public class DagController {

    @Autowired
    private DagService service;

    @ApiOperation("获取画布左侧的算子列表")
    @GetMapping("/listOpGroups")
    public RestResult listOpGroups() {
        List<OperatorAndGroupVO> result = service.listOpGroups();
        return RestResult.success(result);
    }

}