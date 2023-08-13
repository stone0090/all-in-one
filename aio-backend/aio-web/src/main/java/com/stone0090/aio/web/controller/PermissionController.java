package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.PermissionQueryRequest;
import com.stone0090.aio.service.model.web.request.save.PermissionSaveRequest;
import com.stone0090.aio.service.model.web.response.PermissionVO;
import com.stone0090.aio.service.core.system.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "PermissionController", tags = "权限管理")
@RequestMapping("/aio/permission")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @ApiOperation("获取权限列表")
    @GetMapping("/list")
    public RestResult list(PermissionQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<PermissionVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个权限")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        PermissionVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增权限")
    @PostMapping("/add")
    public RestResult add(@RequestBody PermissionSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑权限")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody PermissionSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除权限")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

}