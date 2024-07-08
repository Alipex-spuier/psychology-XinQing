package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.User;
import com.music.mapper.UserMapper;
import com.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String registerService(User user) {
        User userSearch = userMapper.searchByUsername(user.getUsername());
        if (userSearch == null) {
            if ("".equals(user.getPassword())) {
                return "请输入密码";
            } else if ("".equals(user.getUsername())) {
                return "请输入用户昵称";
            } else {
                userMapper.insert(user);
                return "SUCCESS";
            }
        }
        return "用户已存在";
    }

    @Override
    public IPage pageCC(IPage<User> page, Wrapper wrapper) {
        return userMapper.pageCC(page,wrapper);
    }
}
