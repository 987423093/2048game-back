package com.xinyuzang.game.domain.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
@Data
public class RegisterRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "请输入用户名")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "请输入密码")
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "请输入昵称")
    private String nickName;


    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
