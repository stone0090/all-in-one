package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.core.algorithm.ExperimentService;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.*;
import com.stone0090.aio.service.model.web.response.ExperimentBriefVO;
import com.stone0090.aio.service.model.web.response.ExperimentDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "ExperimentController", tags = "画布管理")
@RequestMapping("/aio/experiment")
public class ExperimentController {

    @Autowired
    private ExperimentService service;

    @ApiOperation("获取画布列表")
    @GetMapping("/list")
    public RestResult list(ExperimentQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<ExperimentBriefVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个画布")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        ExperimentDetailVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增画布")
    @PostMapping("/add")
    public RestResult add(@RequestBody ExperimentSaveBriefRequest request) {
        int count = service.saveBrief(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑画布摘要")
    @PostMapping("/editBrief")
    public RestResult editBrief(@RequestBody ExperimentSaveBriefRequest request) {
        int count = service.saveBrief(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑画布详情")
    @PostMapping("/editDetail")
    public RestResult editDetail(@RequestBody ExperimentSaveDetailRequest request) {
        int count = service.saveDetail(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除画布")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

}