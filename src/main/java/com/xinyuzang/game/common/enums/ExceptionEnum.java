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
    TOKEN_INVALID(101, "token失效"),
    /**
     * 未登录
     */
    TOKEN_UN_LOGIN(102, "未登录");


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
