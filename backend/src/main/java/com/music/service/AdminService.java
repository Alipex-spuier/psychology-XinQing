package com.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Admin;

public interface AdminService extends IService<Admin> {
    String registerService(Admin admin);
}
