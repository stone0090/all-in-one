package com.stone0090.aio.web.controller;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.protocal.RestResult;
import com.stone0090.aio.api.request.*;
import com.stone0090.aio.api.response.RoleVO;
import com.stone0090.aio.api.response.SystemConfigVO;
import com.stone0090.aio.service.RoleService;
import com.stone0090.aio.service.SystemConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "RoleController", tags = "配置管理")
@RequestMapping("/aio/sc")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @ApiOperation("获取配置列表")
    @GetMapping("/list")
    public RestResult listConfigs(SystemConfigQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<SystemConfigVO> result = systemConfigService.listConfigs(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个配置")
    @GetMapping("/get")
    public RestResult getConfig(IdentifierRequest request) {
        SystemConfigVO result = systemConfigService.getConfig(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增配置")
    @PostMapping("/add")
    public RestResult addConfig(@RequestBody SystemConfigSaveRequest request) {
        int count = systemConfigService.saveConfig(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑配置")
    @PostMapping("/edit")
    public RestResult editConfig(@RequestBody SystemConfigSaveRequest request) {
        int count = systemConfigService.saveConfig(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除配置")
    @PostMapping("/remove")
    public RestResult removeConfig(@RequestBody IdentifierRequest request) {
        int count = systemConfigService.removeConfig(request);
        return RestResult.success(count);
    }

}