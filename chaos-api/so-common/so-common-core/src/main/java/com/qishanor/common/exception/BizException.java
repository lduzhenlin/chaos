package com.qishanor.common.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException{

    private Integer code=-1;
    private String msg;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
