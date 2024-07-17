package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Admin;
import com.music.entity.User;

public interface AdminService extends IService<Admin> {
    String registerService(Admin admin);
    IPage pageCC(IPage<Admin> page, Wrapper wrapper);
    Admin searchByAdminName(String adminName);
}
