package com.stone0090.aio.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone
 * @date 2021/12/05
 */
@RestController
@Api(value = "HeartbeatController", tags = "心跳检测")
@RequestMapping("/aio/health")
public class HealthController {

    @ApiOperation("健康检查")
    @GetMapping("/check")
    public String check() {
        return "success";
    }

}