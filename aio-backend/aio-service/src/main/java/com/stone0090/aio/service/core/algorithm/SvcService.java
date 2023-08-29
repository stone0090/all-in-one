package com.stone0090.aio.service.core.algorithm;

import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.SvcInvokeRequest;
import org.springframework.validation.annotation.Validated;

/**
 * @author stone
 * @date 2023/06/22
 */
@Validated
public interface SvcService {

    int onlineSvc(IdRequest request);

    int offlineSvc(IdRequest request);

    String invokeSvc(SvcInvokeRequest request);

}
