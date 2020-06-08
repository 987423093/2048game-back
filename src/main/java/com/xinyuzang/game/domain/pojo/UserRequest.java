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
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
