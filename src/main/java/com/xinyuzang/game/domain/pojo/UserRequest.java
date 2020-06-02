package com.xinyuzang.game.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
@Data
public class UserRequest implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
