package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.ConfigQueryRequest;
import com.stone0090.aio.service.model.web.request.save.ConfigSaveRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ConfigVO;
import com.stone0090.aio.service.core.system.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "ConfigController", tags = "配置管理")
@RequestMapping("/aio/config")
public class ConfigController {

    @Autowired
    private ConfigService service;

    @ApiOperation("获取配置列表")
    @GetMapping("/list")
    public RestResult list(ConfigQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<ConfigVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个配置")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        ConfigVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增配置")
    @PostMapping("/add")
    public RestResult add(@RequestBody ConfigSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑配置")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody ConfigSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除配置")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

}