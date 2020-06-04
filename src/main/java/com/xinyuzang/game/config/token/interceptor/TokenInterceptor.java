package com.xinyuzang.game.config.token.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.xinyuzang.game.common.constant.RedisConstant;
import com.xinyuzang.game.common.enums.ExceptionEnum;
import com.xinyuzang.game.config.exception.MyException;
import com.xinyuzang.game.config.token.annotation.UnLoginRequired;
import com.xinyuzang.game.config.token.filter.MyHttpServletRequestWrapper;
import com.xinyuzang.game.domain.entity.User;
import com.xinyuzang.game.service.UserService;
import com.xinyuzang.game.utils.RedisUtil;
import com.xinyuzang.game.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 17:04
 * @Copyright: 2020 Hundsun All rights reserved.
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    private static final String LOGIN = "login";

    /**
     * 前置处理
     * 1.如果有去登录标识
     * 1.1.如果是登录接口，进行登录
     * 1.2.如果不是登录接口，跳过
     * 2.如果没有登录标志
     * 2.1.如果校验不同过，抛出异常
     * 2.2.如果校验通过，跳过
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        UnLoginRequired methodAnnotation = method.getAnnotation(UnLoginRequired.class);
        // 如果加上了就跳过登录验证
        if (methodAnnotation != null) {
            // 如果是登录接口，进行登录，并且将token塞到response的header里面的Set-Cookie里面
            if (LOGIN.equals(method.getName())) {
                this.doLogin(request, response);
            }
            return true;
        }
        // 从cookie里面获取token校验token是否有效
        this.verifyToken(request);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 做登录操作
     * @param request
     * @param response
     * @throws Exception
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //解析用户名密码信息
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb = sb.append(str);
        }
        br.close();
        Gson gson = new Gson();
        Map map = gson.fromJson(sb.toString(), HashMap.class);

        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if (StringUtils.isEmpty(username)) {
            throw new MyException(ExceptionEnum.USERNAME_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            throw new MyException(ExceptionEnum.PASSWORD_EMPTY);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", map.get("username"));
        queryWrapper.eq("password", map.get("password"));
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            throw new MyException(ExceptionEnum.LOGIN_FAILED);
        }
        // 登录成功，创建token
        String token = TokenUtil.createJwtToken(user.getUserId());
        try(Jedis jedis = redisUtil.getJedisPool().getResource()) {
            jedis.setex(RedisConstant.TOKEN_PREFIX + user.getUserId(), RedisConstant.EXPIRE_TIME, token);
        }
        response.setHeader("Set-Cookie", "token=" + token);
        // 将userId带到全局入参里面
        MyHttpServletRequestWrapper requestWrapper = (MyHttpServletRequestWrapper) request;
        requestWrapper.addUserId(user.getUserId());
    }

    /**
     * 校验token有效性
     * @param request
     */
    private void verifyToken(HttpServletRequest request) {

        // 1.判断用户是否登录过，前端是否传过来token
        Cookie[] cookies = request.getCookies();
        String token = cookies[0].getValue();
        if (StringUtils.isEmpty(token)) {
            throw new MyException(ExceptionEnum.TOKEN_UN_LOGIN);
        }
        // 2.校验token有效性
        // 判断redis里面token与当前传入token是否一致
        String userId = TokenUtil.parseJWT(token);
        String redisToken;
        try(Jedis jedis = redisUtil.getJedisPool().getResource()){
            redisToken = jedis.get(RedisConstant.TOKEN_PREFIX + userId);
        }
        if (!Objects.equals(redisToken, token)) {
            throw new MyException(ExceptionEnum.TOKEN_INVALID);
        }
        // 将userId带到全局入参里面
        MyHttpServletRequestWrapper requestWrapper = (MyHttpServletRequestWrapper) request;
        requestWrapper.addUserId(Integer.valueOf(userId));
    }
}
