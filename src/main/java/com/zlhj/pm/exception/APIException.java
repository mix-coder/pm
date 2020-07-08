package com.zlhj.pm.exception;

import lombok.Getter;

/**
 * 自定义异常
 * @author tzm
 * @version 1.0
 * @since 2020/5/6 18:07
 */
@Getter
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}