package com.xinyuzang.game.common.constant;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 15:28
 * @Copyright: 2020 Hundsun All rights reserved.
 */
public class RedisConstant {

    public static final String TOKEN_PREFIX = "user:token:";

    /**
     * redis十天key过期时间
     */
    public static final int EXPIRE_TIME = 10 * 24 * 60 * 60 * 1000;
}
