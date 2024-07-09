package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Admin;
import com.music.mapper.AdminMapper;
import com.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public String registerService(Admin admin) {
        Admin adminSearch = adminMapper.searchByAdminName(admin.getAdminName());
        if (adminSearch == null) {
            if ("".equals(admin.getAdminPassword())) {
                return "请输入密码";
            } else if ("".equals(admin.getAdminName())) {
                return "请输入管理员昵称";
            } else {
                adminMapper.insert(admin);
                return "SUCCESS";
            }
        }
        return "用户已存在";
    }

    @Override
    public IPage pageCC(IPage<Admin> page, Wrapper wrapper) {
        return adminMapper.pageCC(page,wrapper);
    }
}
