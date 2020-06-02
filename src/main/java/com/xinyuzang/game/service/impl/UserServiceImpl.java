package com.xinyuzang.game.service.impl;

import com.xinyuzang.game.domain.entity.User;
import com.xinyuzang.game.mapper.UserMapper;
import com.xinyuzang.game.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XinYuZang
 * @since 2020-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
