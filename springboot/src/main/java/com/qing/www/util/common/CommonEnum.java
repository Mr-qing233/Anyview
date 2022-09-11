package com.qing.www.util.common;

/**
 * 返回码枚举类
 */
public enum CommonEnum implements BaseErrorInfoInterface{
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    ERROR("500","系统错误"),
    USER_EMAIL_HAS_EXISTED("1000","用户邮箱已存在"),
    USER_NOT_FOUND("1001","用户不存在"),
    USER_EMAIL_NOT_FOUND("1002","该邮箱未注册"),
    USER_PASSWORD_ERROR("1003","用户邮箱或密码不正确"),
    USER_PERMISSION_DENIED("1004","用户权限不足"),
    USER_MESSAGE_NOT_ENOUGH("1005","用户参数不足"),
    USER_HAS_BEEN_BANNED("1006","用户已被锁定"),
    USER_CANCELLED("1007","用户已注销"),
    TOKEN_NOT_FOUND("1500","token不存在,请重新登录"),
    TOKEN_VALIDATE_ERROR("1501","token验证失败"),
    ERROR_SEARCH_SERVICE("2001","查询业务错误"),
    EMPTY_EMAIL_PARAMETER("3001","缺少邮箱参数"),
    REGISTER_CODE_HAS_EXISTED("3101","注册邮件验证码已存在,请勿重复发送"),
    LOGIN_CODE_HAS_EXISTED("3102","登录邮件验证码已存在,请勿重复发送"),
    FIND_PASSWORD_CODE_HAS_EXISTED("3103","找回密码邮件验证码已存在,请勿重复发送"),
    CODE_ERROR("3104","验证码错误请点击按钮重新发送");

    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
