package com.zhihao.common.exception;

import com.zhihao.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/*
 * 精简处理异常消息
 * */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * 自定义业务异常
     * */
    @ResponseStatus
    @ExceptionHandler(ServiceException.class)
    public R<Void> handlerServiceException(ServiceException e, HttpServletRequest request) {
        Integer code = e.getCode();
        String msg = e.getMsg();
        log.warn("请求地址:'{}',业务异常,msg:'{}'", request.getRequestURI(), msg);
        return R.error(code, msg);
    }

    /*
     * 表单校验异常
     * */
    @ResponseStatus
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.warn("请求地址:'{}',表单校验异常,msg:'{}'", request.getRequestURI(), msg);
        return R.error(msg);
    }

}
