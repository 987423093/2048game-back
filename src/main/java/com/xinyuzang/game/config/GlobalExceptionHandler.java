package com.xinyuzang.game.config;

import com.xinyuzang.game.domain.common.ApiResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 13:19
 * @Copyright: 2020 Hundsun All rights reserved.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 入参校验全局异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult requestExceptionHandler(MethodArgumentNotValidException exception) {

        FieldError fieldError = exception.getBindingResult().getFieldError();
        return ApiResult.error(fieldError == null ? "" : fieldError.getDefaultMessage());
    }

    /**
     * 统一异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult globalExceptionHandler(Exception exception) {

        return ApiResult.error("服务器异常");
    }

    /**
     * 自定义异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ApiResult myExceptionHandler(MyException exception) {

        return ApiResult.error(exception.getMessage());
    }
}
