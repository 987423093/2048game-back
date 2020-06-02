package com.xinyuzang.game.domain.pojo;

import lombok.Data;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
@Data
public class UserResponse {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;
}
