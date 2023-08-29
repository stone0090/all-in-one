package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.core.algorithm.DagService;
import com.stone0090.aio.service.core.algorithm.SvcService;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.DagQueryRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.SvcInvokeRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveDetailRequest;
import com.stone0090.aio.service.model.web.response.DagBriefVO;
import com.stone0090.aio.service.model.web.response.DagDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "DagController", tags = "Dag管理")
@RequestMapping("/aio/dag")
public class DagController {

    @Autowired
    private DagService service;

    @Autowired
    @Qualifier("dagService")
    private SvcService svcService;

    @ApiOperation("获取Dag列表")
    @GetMapping("/list")
    public RestResult list(DagQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<DagBriefVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个Dag")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        DagDetailVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增Dag")
    @PostMapping("/add")
    public RestResult add(@RequestBody DagSaveBriefRequest request) {
        int count = service.saveBrief(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑Dag摘要")
    @PostMapping("/editBrief")
    public RestResult editBrief(@RequestBody DagSaveBriefRequest request) {
        int count = service.saveBrief(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑Dag详情")
    @PostMapping("/editDetail")
    public RestResult editDetail(@RequestBody DagSaveDetailRequest request) {
        int count = service.saveDetail(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除Dag")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

    @ApiOperation("上线Dag服务")
    @PostMapping("/onlineSvc")
    public RestResult online(@RequestBody IdRequest request) {
        int count = svcService.onlineSvc(request);
        return RestResult.success(count);
    }

    @ApiOperation("下线Dag服务")
    @PostMapping("/offlineSvc")
    public RestResult offline(@RequestBody IdRequest request) {
        int count = svcService.offlineSvc(request);
        return RestResult.success(count);
    }

    @ApiOperation("调用Dag服务")
    @PostMapping("/invokeSvc")
    public RestResult invokeApi(@RequestBody SvcInvokeRequest request) {
        String result = svcService.invokeSvc(request);
        return RestResult.success(result);
    }

}