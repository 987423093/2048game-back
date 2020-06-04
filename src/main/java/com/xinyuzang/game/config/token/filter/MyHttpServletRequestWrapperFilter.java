package com.xinyuzang.game.config.token.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/4 14:08
 * @Copyright: 2020 Hundsun All rights reserved.
 */
@Component
public class MyHttpServletRequestWrapperFilter implements Filter {

    /**
     * 全局过滤把ServletRequest都置成自定义的
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            httpServletRequest = new MyHttpServletRequestWrapper(httpServletRequest);
            filterChain.doFilter(httpServletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
