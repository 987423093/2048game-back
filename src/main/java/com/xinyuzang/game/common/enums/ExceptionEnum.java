package com.xinyuzang.game.common.enums;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 17:13
 * @Copyright: 2020 Hundsun All rights reserved.
 */
public enum ExceptionEnum {

    /**
     * 服务器异常
     */
    SERVER_ERROR(500, "服务器异常"),
    /**
     * token失效
     */
    TOKEN_INVALID(101, "用户已失效，请重新登录"),
    /**
     * 未登录
     */
    TOKEN_UN_LOGIN(102, "未登录"),
    /**
     * 账号或者密码错误
     */
    LOGIN_FAILED(103, "账号或者密码错误"),
    /**
     * 用户名称为空
     */
    USERNAME_EMPTY(104, "请输入用户名"),
    /**
     * 用户密码为空
     */
    PASSWORD_EMPTY(105, "请输入密码");


    private int code;

    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
