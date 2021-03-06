package com.base.common.exception;

/**
 * Created by ace on 2017/9/8.
 */
public class BaseException extends RuntimeException {
    private int code = 200;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseException() {
    }

    public BaseException(String message,int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
