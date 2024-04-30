package com.music.service;

import com.music.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注官网：同学邦
 * @since 2023-07-21
 */
public interface UserService extends IService<User> {
    public String registerService(User user);
}
