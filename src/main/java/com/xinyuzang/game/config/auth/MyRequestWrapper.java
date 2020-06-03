package com.xinyuzang.game.config.auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author zhoutao
 * @date 2020/6/3
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;
    private String bodyStr;

    public MyRequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
