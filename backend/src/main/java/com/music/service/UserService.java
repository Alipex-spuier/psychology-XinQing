package com.music.service;

import com.music.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    public String registerService(User user);
}
