package com.stone0090.aio.web.controller;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.protocal.RestResult;
import com.stone0090.aio.api.request.IdRequest;
import com.stone0090.aio.api.request.RoleQueryRequest;
import com.stone0090.aio.api.request.RoleSaveRequest;
import com.stone0090.aio.api.response.RoleVO;
import com.stone0090.aio.service.user.RoleService;
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
@Api(value = "RoleController", tags = "角色管理")
@RequestMapping("/aio/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @ApiOperation("获取角色列表")
    @GetMapping("/list")
    public RestResult list(RoleQueryRequest queryRequest, PageRequest pageRequest) {
        PageResult<RoleVO> result = service.list(queryRequest, pageRequest);
        return RestResult.success(result);
    }

    @ApiOperation("获取单个角色")
    @GetMapping("/get")
    public RestResult get(IdRequest request) {
        RoleVO result = service.get(request);
        return RestResult.success(result);
    }

    @ApiOperation("新增角色")
    @PostMapping("/add")
    public RestResult add(@RequestBody RoleSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("编辑角色")
    @PostMapping("/edit")
    public RestResult edit(@RequestBody RoleSaveRequest request) {
        int count = service.save(request);
        return RestResult.success(count);
    }

    @ApiOperation("移除角色")
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IdRequest request) {
        int count = service.remove(request);
        return RestResult.success(count);
    }

}