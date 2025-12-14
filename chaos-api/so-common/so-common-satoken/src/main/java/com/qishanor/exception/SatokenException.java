package com.qishanor.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.qishanor.common.exception.BizException;
import com.qishanor.common.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SatokenException {

    @ExceptionHandler(NotLoginException.class)
    public Object notLoginExceptionHandler(NotLoginException e) {

        return R.failed(e.getMessage());
    }


}
