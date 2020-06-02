package com.xinyuzang.game.domain.common;

import java.io.Serializable;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
public class ApiResult<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    private ApiResult() {

    }

    /**
     * 失败
     * @param message
     * @return
     */
    public static <T>ApiResult<T> error(String message) {

        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(500);
        apiResult.setMessage(message);
        return apiResult;
    }

    /**
     * 成功带参数
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ApiResult<T> success(T data) {

        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setMessage("成功");
        apiResult.setCode(0);
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * 成功不带参数
     * @return
     */
    public static ApiResult success() {

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(0);
        apiResult.setMessage("成功");
        return apiResult;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
