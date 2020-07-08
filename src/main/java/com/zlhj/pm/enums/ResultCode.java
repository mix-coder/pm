package com.zlhj.pm.enums;

import lombok.Getter;

/**
 * @author tzm
 * @version 1.0
 * @since 2020/5/6 17:53
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}