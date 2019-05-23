package com.base.common.enums;

/**
 * @author liuyanting
 * @description 返回
 * @date: 2018/12/20
 */
public enum ValidStatusEnum {
    YES(1, "是"),
    NO(0, "否");

    private final int code;
    private final String msg;

    public static final String VALID_STATUS = "validStatus";

    ValidStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isEqual(Integer code){
        return code  != null && code.equals(this.code);
    }

    public static boolean isYes(Integer code){
        return code!= null && code.equals(ValidStatusEnum.YES.code);
    }
}
