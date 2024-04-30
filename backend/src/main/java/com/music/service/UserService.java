package com.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.User;

public interface UserService extends IService<User> {
    public String registerService(User user);
}
