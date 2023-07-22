package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.request.ApiInvokeRequest;
import com.stone0090.aio.service.model.web.request.ApiRequest;
import org.springframework.validation.annotation.Validated;

/**
 * @author stone
 * @date 2023/06/22
 */
@Validated
public interface ApiService {

    int onlineApi(ApiRequest request);

    int offlineApi(ApiRequest request);

    String invokeApi(ApiInvokeRequest request);

}
