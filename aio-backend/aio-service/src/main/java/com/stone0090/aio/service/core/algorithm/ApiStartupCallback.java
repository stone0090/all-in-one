package com.stone0090.aio.service.core.algorithm;

import java.util.Map;

public interface ApiStartupCallback {
    void inject(Map<String, String> deployInfo);
}
