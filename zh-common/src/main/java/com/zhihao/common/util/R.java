package com.zhihao.common.util;

import org.apache.hc.core5.http.HttpStatus;

public class R<T> {

    public static final int SUCCESS = HttpStatus.SC_SUCCESS;

    public static final int SERVER_ERROR = HttpStatus.SC_SERVER_ERROR;

    private static final String SUCCESS_MSG = "success";

    private static final String SERVER_ERROR_MSG = "server_error";

    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private static <T> R<T> restResult(int code, String msg, T data) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> R<T> ok() {
        return restResult(SUCCESS, SUCCESS_MSG, null);
    }

    public static <T> R<T> ok(String msg) {
        return restResult(SUCCESS, msg, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return restResult(SUCCESS, msg, data);
    }

    public static <T> R<T> error() {
        return restResult(SERVER_ERROR, SERVER_ERROR_MSG, null);
    }

    public static <T> R<T> error(String msg) {
        return restResult(SERVER_ERROR, msg, null);
    }

    public static <T> R<T> error(T data) {
        return restResult(SERVER_ERROR, SERVER_ERROR_MSG, data);
    }

    public static <T> R<T> error(String msg, T data) {
        return restResult(SERVER_ERROR, msg, data);
    }

    public static <T> R<T> error(int code) {
        return restResult(code, SERVER_ERROR_MSG, null);
    }

    public static <T> R<T> error(int code, String msg) {
        return restResult(code, msg, null);
    }

    public static <T> R<T> error(int code, T data) {
        return restResult(code, SERVER_ERROR_MSG, data);
    }

    public static <T> R<T> error(int code, String msg, T data) {
        return restResult(code, msg, data);
    }

}
