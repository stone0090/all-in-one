package com.stone0090.aio.web.controller;

import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.request.ApiInvokeRequest;
import com.stone0090.aio.service.model.web.request.ApiRequest;
import com.stone0090.aio.service.core.algorithm.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone
 * @date 2021/08/02
 */
@RestController
@Api(value = "ApiController", tags = "API管理")
@RequestMapping("aio/api")
public class ApiController {

//    @Autowired
//    private ApiService apiService;
//
//    @ApiOperation("API上线")
//    @PostMapping("/online")
//    public RestResult online(@RequestBody ApiRequest request) {
//        int count = apiService.onlineApi(request);
//        return RestResult.success(count);
//    }
//
//    @ApiOperation("API上线")
//    @PostMapping("/offline")
//    public RestResult offline(@RequestBody ApiRequest request) {
//        int count = apiService.offlineApi(request);
//        return RestResult.success(count);
//    }
//
//    @ApiOperation("API调用")
//    @PostMapping("/invoke")
//    public RestResult invokeApi(@RequestBody ApiInvokeRequest request) {
//        String result = apiService.invokeApi(request);
//        return RestResult.success(result);
//    }

}