package com.xinyuzang.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinyuzang.game.config.token.annotation.UnLoginRequired;
import com.xinyuzang.game.domain.common.ApiResult;
import com.xinyuzang.game.domain.common.BaseRequest;
import com.xinyuzang.game.domain.entity.User;
import com.xinyuzang.game.domain.pojo.UserRequest;
import com.xinyuzang.game.domain.pojo.UserResponse;
import com.xinyuzang.game.service.UserService;
import com.xinyuzang.game.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author XinYuZang
 * @since 2020-06-01
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param userRequest
     * @return
     */
    @PostMapping("login")
    @UnLoginRequired
    public ApiResult<UserResponse> login(@RequestBody @Validated UserRequest userRequest) {

        User user = userService.getById(userRequest.getUserId());
        if (user == null) {
            return ApiResult.error("请输入正确的账号密码");
        }
        return ApiResult.success(CopyUtils.copyProperties(user, UserResponse.class));
    }

    /**
     * 注册
     *
     * @param userRequest
     * @return
     */
    @PostMapping("register")
    @UnLoginRequired
    public ApiResult register(@RequestBody @Validated UserRequest userRequest) {

        log.info(userRequest.toString());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userRequest.getUsername());
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return ApiResult.error("该用户名已存在");
        }
        User registerUser = new User();
        registerUser.setUsername(userRequest.getUsername());
        registerUser.setPassword(userRequest.getPassword());
        registerUser.setNickName(userRequest.getUsername());
        userService.save(registerUser);
        return ApiResult.success();
    }

    /**
     * 获取用户信息
     *
     * @param baseRequest
     * @return
     */
    @PostMapping("getUserDetail")
    public ApiResult<UserResponse> getUserDetail(@RequestBody BaseRequest baseRequest) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", baseRequest.getUserId());
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return ApiResult.error("该用户不存在");
        }
        UserResponse userResponse = CopyUtils.copyProperties(user, UserResponse.class);
        return ApiResult.success(userResponse);
    }

    /**
     * 登出
     * @param baseRequest
     * @return
     */
    @PostMapping("logout")
    public ApiResult logout(@RequestBody BaseRequest baseRequest) {

        return null;
    }
}

