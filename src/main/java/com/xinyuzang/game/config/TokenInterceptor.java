package com.xinyuzang.game.config;

import com.xinyuzang.game.common.constant.RedisConstant;
import com.xinyuzang.game.common.enums.ExceptionEnum;
import com.xinyuzang.game.utils.RedisUtil;
import com.xinyuzang.game.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 17:04
 * @Copyright: 2020 Hundsun All rights reserved.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private static List<String> WHITE_LIST = Arrays.asList("/user/login", "/user/register");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截白名单：登录，注册
        if (WHITE_LIST.contains(request.getMethod())) {
            return true;
        }
        // 判断用户是否登录过，存在token
        String token = request.getParameter("token");
        if (token == null) {
            throw new MyException(ExceptionEnum.TOKEN_UN_LOGIN);
        }
        //判断redis里面token与当前传入token是否一致
        String userId = TokenUtil.parseJWT(token);
        String redisToken;
        try(Jedis jedis = redisUtil.getJedisPool()){
            redisToken = jedis.get(RedisConstant.TOKEN_PREFIX + userId);
        }
        if (Objects.equals(redisToken, token)) {
            throw new MyException(ExceptionEnum.TOKEN_INVALID);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
