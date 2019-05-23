package com.base.common.enums;

/**
 * @author liuyanting
 * @description 返回
 * @date: 2018/12/20
 */
public enum ErrorCodeEnum {
    /***成功***/
    SUCCESS(200, "操作成功"),
    ADD_SUCCESS(200, "添加成功"),
    UPDATE_SUCCESS(200, "更新成功"),
    DELETE_SUCCESS(200, "删除成功"),
    REGISTERED_SUCCESS(200, "注册成功"),
    VALIDATE_SUCCESS(200, "验证通过"),
    SEND_MAIL_SUCCESS(200, "邮件发送成功"),
    SEND_SMS_SUCCESS(200, "短信发送成功"),
    IMPORT_SUCCESS(200, "导入成功"),

    ADD_FAILED(-1, "添加失败"),
    UPDATE_FAILED(-2, "更新失败"),
    DELETE_FAILED(-3, "删除失败"),
    UNIQUE_FAILED(-4, "唯一性校验失败"),

    APPLY_INVALID_FAILED(-5, "申请已失效"),
    VALIDATE_CAPTCHA_FAILED(-6, "验证码错误或失效"),

    VALIDATE_ACCOUNT_PASSWORD_NOT_NULL(-7, "账号密码不能为空"),
    VALIDATE_ACCOUNT_NOT_EXISTS(-7, "账号不存在"),
    VALIDATE_PASSWORD_NOT_MATCH(-7, "密码错误"),
    VALIDATE_ACCOUNT_OR_PASSWORD_FAILED(-7, "账号或密码错误"),
    VALIDATE_FAILED(-7, "验证失败"),
    VALIDATE_ACCOUNT_FROZEN(-7, "账号已冻结"),

    ID_NOT_NULL(-8,"id不能为空"),
    RECORD_NOT_EXISTS(-9,"记录不存在"),

    GET_FILE_FAILED(-10, "读取文件失败"),
    GET_TEMPLATE_FAILED(-10, "读取模板失败"),
    FILE_NOT_EXISTS(-11, "文件不存在"),
    IMPORT_FAILED(-12, "导入失败"),

    REGISTER_FAILED(-601, "注册失败"),
    DATA_REPEAT(-602, "记录已存在"),
    USER_REPEAT(-603, "用户已存在"),
    COMPANY_REPEAT(-604, "企业已存在"),

    SEND_MAIL_FAILED(-701, "邮件发送失败"),
    SEND_SMS_FAILED(-702, "短信发送失败"),

    MESSAGE_SERVICE_EXCEPTION(-500, "服务异常"),

    FAILED(-200, "操作失败"),
    USER_INVALIDATE(-201, "用户信息丢失, 请重新登陆后尝试"),
    ILLEGAL_FAILED(-999, "违规操作");

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
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
}
