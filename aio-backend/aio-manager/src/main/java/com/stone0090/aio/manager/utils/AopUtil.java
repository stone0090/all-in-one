package com.stone0090.aio.manager.utils;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

public class AopUtil {

    public static Object log(ProceedingJoinPoint joinPoint, String url, Logger logger, Logger digestLogger) {
        // 1. 获取类名、方法名、参数名、参数值
        Class<?> klass = joinPoint.getTarget().getClass();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String serviceName = signature.getDeclaringType().getSimpleName();
        String[] parameterNames = null;
        try {
            parameterNames = ClassUtil.getFiledName(klass, methodName);
        } catch (NotFoundException e) {
            // do nothing
        }
        Object[] args = joinPoint.getArgs();

        // 2. 请求日志
        if (parameterNames != null){
            if (StringUtils.isEmpty(url)) {
                logger.info("{}#{}, req:{}", serviceName, methodName, merge(parameterNames, args));
            } else {
                logger.info("[{}], {}#{}, req:{}", url, serviceName, methodName, merge(parameterNames, args));
            }
        }

        // 3. 耗时日志
        StopWatch stopWatch = new StopWatch();
        int hasError = 0;
        try {
            stopWatch.start();
            return joinPoint.proceed();
        } catch (Throwable e) {
            hasError = 1;
            throw new RuntimeException(e);
        } finally {
            stopWatch.stop();
            if (StringUtils.isEmpty(url)) {
                digestLogger.info("{}#{}, err:{}, cost:{}ms", serviceName, methodName, hasError, stopWatch.getTotalTimeMillis());
            } else {
                digestLogger.info("[{}], {}#{}, err:{}, cost:{}ms", url, serviceName, methodName, hasError, stopWatch.getTotalTimeMillis());
            }
        }
    }

    private static Object merge(String[] parameterNames, Object[] args) {
        if (parameterNames != null && args != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                sb.append(parameterNames[i]).append("=").append(args[i]).append(",");
            }
            return sb.toString();
        }
        return Strings.EMPTY;
    }
}
