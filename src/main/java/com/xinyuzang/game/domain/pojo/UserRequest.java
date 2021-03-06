package com.xinyuzang.game.domain.pojo;

import com.xinyuzang.game.domain.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
@Data
public class UserRequest extends BaseRequest {

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
    private String nickName;

    /**
     * token
     */
    private String token;

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
