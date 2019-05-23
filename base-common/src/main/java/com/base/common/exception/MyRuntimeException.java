package com.base.common.exception;

/**
 * @author liuyanting
 * @description 运行时异常
 * @date: 2019/01/11
 */
public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String msg){
        super(msg);
    }
}
