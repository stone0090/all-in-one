package com.stone0090.aio.web.config;

import com.stone0090.aio.manager.util.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebAop {

    private final Logger logger = LoggerFactory.getLogger("WEB_LOG");
    private final Logger digestLogger = LoggerFactory.getLogger("WEB_DIGEST_LOG");

    @Around("execution(* com.stone0090.aio.web.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 1. 获取请求的url
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        String url = attributes.getRequest().getRequestURL().toString();
        // 2. 打印aop日志
        return AopUtil.log(joinPoint, url, logger, digestLogger);
    }

}
