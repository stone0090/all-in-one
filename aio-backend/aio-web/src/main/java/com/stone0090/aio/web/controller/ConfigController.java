package com.stone0090.aio.web.controller;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.protocal.RestResult;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.ConfigVO;
import com.stone0090.aio.service.system.ConfigService;
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
    private ConfigService configService;

    @ApiOperation("获取配置列表")
    @GetMapping("/list")
    public RestResult list(ConfigQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<ConfigVO> result = configService.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个配置")
    @GetMapping("/get")
    public RestResult get(IdentifierRequest request) {
        ConfigVO result = configService.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增配置")
    @PostMapping("/add")
    public RestResult add(@RequestBody ConfigSaveRequest request) {
        int count = configService.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑配置")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody ConfigSaveRequest request) {
        int count = configService.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除配置")
    @PostMapping("/remove")
    public RestResult removeConfig(@RequestBody IdentifierRequest request) {
        int count = configService.remove(request);
        return RestResult.success(count);
    }

}