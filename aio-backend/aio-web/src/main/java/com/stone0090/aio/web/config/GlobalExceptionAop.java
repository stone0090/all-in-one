package com.stone0090.aio.web.config;

import javax.validation.ConstraintViolationException;

import com.stone0090.aio.service.model.web.protocal.RestResult;
import com.stone0090.aio.service.model.web.protocal.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author stone
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAop {

    /**
     * 统一处理 hibernate Validator 抛出来的参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody
    RestResult handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(x -> message.append(x.getMessage()).append("\n"));
        return RestResult.failure(message.toString());
    }

    /**
     * 统一处理 hibernate Validator 抛出来的参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody
    RestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            message.append(error.getDefaultMessage()).append("\n");
        }
        return RestResult.failure(message.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    RestResult handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException", e);
        return RestResult.failure(e.getLocalizedMessage().replace("java.lang.RuntimeException: ", ""));
        // return RestResult.failure("系统繁忙，请稍后再试！");
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    RestResult handleCustomException(Exception e) {
        log.error("Exception", e);
        // return RestResult.failure(e.getLocalizedMessage());
        return RestResult.failure("系统繁忙，请稍后再试！");
    }

}
