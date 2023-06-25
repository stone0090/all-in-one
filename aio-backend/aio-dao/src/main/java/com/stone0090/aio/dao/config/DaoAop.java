package com.stone0090.aio.dao.config;

import com.stone0090.aio.manager.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAop {

    private final Logger logger = LoggerFactory.getLogger("DAO_LOG");
    private final Logger digestLogger = LoggerFactory.getLogger("DAO_DIGEST_LOG");

    @Around("execution(* com.stone0090.aio.dao.mybatis.mapper..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        return AopUtil.log(joinPoint, null, logger, digestLogger);
    }

}
