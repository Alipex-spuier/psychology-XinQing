package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.User;

public interface UserService extends IService<User> {
    public String registerService(User user);
    IPage pageCC(IPage<User> page, Wrapper wrapper);
}
