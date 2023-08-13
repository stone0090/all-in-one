package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.core.algorithm.SvcService;
import com.stone0090.aio.service.core.system.ConfigService;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.OperatorQueryRequest;
import com.stone0090.aio.service.model.web.request.SvcInvokeRequest;
import com.stone0090.aio.service.model.web.request.SvcRequest;
import com.stone0090.aio.service.model.web.request.save.OperatorSaveRequest;
import com.stone0090.aio.service.model.web.response.OperatorAndGroupVO;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import com.stone0090.aio.service.core.algorithm.OperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "OperatorController", tags = "算子管理")
@RequestMapping("/aio/operator")
public class OperatorController {

    @Autowired
    private OperatorService service;

    @Autowired
    @Qualifier("operatorService")
    private SvcService svcService;

    @Autowired
    private ConfigService configService;

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

    @ApiOperation("发布算子")
    @PostMapping("/publish")
    public RestResult publish(@RequestBody IdRequest request) {
        int count = service.publish(request);
        return RestResult.success(count);
    }

    @ApiOperation("废弃算子")
    @PostMapping("/deprecate")
    public RestResult deprecate(@RequestBody IdRequest request) {
        int count = service.deprecate(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除算子")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

    @ApiOperation("获取Dag左侧的算子列表")
    @GetMapping("/listDagOpGroups")
    public RestResult listDagOpGroups() {
        List<OperatorAndGroupVO> result = service.listDagOpGroups();
        return RestResult.success(result);
    }

    @ApiOperation("获取默认配置")
    @GetMapping("/getDefaultConfig")
    public RestResult getDefaultConfig() {
        OperatorVO result = configService.getOperatorDefaultConfig();
        return RestResult.success(result);
    }

    @ApiOperation("上线算子服务")
    @PostMapping("/onlineSvc")
    public RestResult online(@RequestBody SvcRequest request) {
        int count = svcService.onlineSvc(request);
        return RestResult.success(count);
    }

    @ApiOperation("下线算子服务")
    @PostMapping("/offlineSvc")
    public RestResult offline(@RequestBody SvcRequest request) {
        int count = svcService.offlineSvc(request);
        return RestResult.success(count);
    }

    @ApiOperation("调用算子服务")
    @PostMapping("/invokeSvc")
    public RestResult invokeApi(@RequestBody SvcInvokeRequest request) {
        String result = svcService.invokeSvc(request);
        return RestResult.success(result);
    }

}