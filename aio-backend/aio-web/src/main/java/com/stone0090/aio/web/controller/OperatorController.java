package com.stone0090.aio.web.controller;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.protocal.RestResult;
import com.stone0090.aio.api.request.IdRequest;
import com.stone0090.aio.api.request.OperatorQueryRequest;
import com.stone0090.aio.api.request.OperatorSaveRequest;
import com.stone0090.aio.api.response.OperatorVO;
import com.stone0090.aio.service.core.OperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "ConfigController", tags = "算子管理")
@RequestMapping("/aio/operator")
public class OperatorController {

    @Autowired
    private OperatorService service;

    @ApiOperation("获取算子列表")
    @GetMapping("/list")
    public RestResult list(OperatorQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<OperatorVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个算子")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        OperatorVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增算子")
    @PostMapping("/add")
    public RestResult add(@RequestBody OperatorSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑算子")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody OperatorSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除算子")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

    @ApiOperation("获取默认配置")
    @GetMapping("/config/get")
    public RestResult getConfig() {
        OperatorVO result = service.getConfig();
        return RestResult.success(result);
    }

    @ApiOperation("发布API")
    @PostMapping("/publish")
    public RestResult publishApi(@RequestBody IdRequest request) {
        int count = service.publishApi(request);
        return RestResult.success(count);
    }

}