package com.xinyuzang.game.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinyuzang.game.domain.common.ApiResult;
import com.xinyuzang.game.domain.entity.User;
import com.xinyuzang.game.domain.pojo.UserRequest;
import com.xinyuzang.game.domain.pojo.UserResponse;
import com.xinyuzang.game.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
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
     * @param userRequest
     * @return
     */
    @PostMapping("login")
    public ApiResult<UserResponse> login(@RequestBody UserRequest userRequest) {

        if (userRequest == null) {
            return ApiResult.error("登陆失败，请输入账号密码");
        }
        if (StringUtils.isEmpty(userRequest.getUsername())) {
            return ApiResult.error("登陆失败，请输入用户名");
        }
        if (StringUtils.isEmpty(userRequest.getPassword())) {
            return ApiResult.error("登陆失败，请输入密码");
        }

        log.info(userRequest.toString());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userRequest.getUsername());
        queryWrapper.eq("password", userRequest.getPassword());
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return ApiResult.error("请输入正确的账号密码");
        }
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return ApiResult.success(userResponse);
    }

    /**
     * 注册
     * @param userRequest
     * @return
     */
    @PostMapping("register")
    public ApiResult register(@RequestBody UserRequest userRequest) {

        if (userRequest == null) {
            return ApiResult.error("注册失败，请输入账号密码");
        }
        if (StringUtils.isEmpty(userRequest.getUsername())) {
            return ApiResult.error("注册失败，请输入用户名");
        }
        if (StringUtils.isEmpty(userRequest.getPassword())) {
            return ApiResult.error("注册失败，请输入密码");
        }

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

}

