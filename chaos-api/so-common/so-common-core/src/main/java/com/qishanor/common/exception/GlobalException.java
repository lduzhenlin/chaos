package com.qishanor.common.exception;

import com.qishanor.common.util.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description: 全局异常配置
 * @author: 周振林
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 处理请求参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //1、result 中封装了所有错误信息
        BindingResult result = ex.getBindingResult();

        List<FieldError> errors = result.getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError error : errors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            map.put(field, message);
        }

        return R.failed(map, "参数错误");
    }


    @ExceptionHandler(BizException.class)
    public Object bizExceptionHandler(BizException e) {
        return R.failed(e.getMessage());
    }

    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object globalException(Exception e){

        // 打印堆栈，以供调试
        log.error("异常信息：",e);
        e.printStackTrace();
        return R.failed(e.getMessage());
    }



}
