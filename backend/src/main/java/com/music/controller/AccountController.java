package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.music.Utils.JwtUtils;
import com.music.common.dto.AdminLoginDto;
import com.music.common.dto.ExpertLoginDto;
import com.music.common.dto.LoginDto;
import com.music.common.lang.Result;
import com.music.entity.Admin;
import com.music.entity.Expert;
import com.music.entity.User;
import com.music.service.AdminService;
import com.music.service.ExpertService;
import com.music.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    ExpertService expertService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", loginDto.getUserName()));
        Assert.notNull(user, "用户不存在");
        if (!user.getUserPassword().equals(SecureUtil.md5(loginDto.getUserPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getUserId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        userService.updateById(user);
        return Result.succ(MapUtil.builder()
                .put("userId", user.getUserId())
                .put("userName", user.getUserName())
                .put("userEmail", user.getUserEmail())
                .put("userIntro", user.getUserIntro())
                .put("createdTime", user.getCreatedTime())
                .map()
        );
    }

    @PostMapping("/adminLogin")
    public Result adminLogin(@Validated @RequestBody AdminLoginDto adminLoginDto, HttpServletResponse response) {
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("admin_name", adminLoginDto.getAdminName()));
        Assert.notNull(admin, "管理员不存在");
        if (!admin.getAdminPassword().equals(SecureUtil.md5(adminLoginDto.getAdminPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(admin.getAdminId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        adminService.updateById(admin);
        return Result.succ(MapUtil.builder()
                .put("adminId", admin.getAdminId())
                .put("adminName", admin.getAdminName())
                .put("adminEmail", admin.getAdminEmail())
                .put("createdTime", admin.getCreatedTime())
                .map()
        );
    }

    @PostMapping("/expertLogin")
    public Result expertLogin(@Validated @RequestBody ExpertLoginDto expertLoginDto, HttpServletResponse response) {
        Expert expert = expertService.getOne(new QueryWrapper<Expert>().eq("ex_name", expertLoginDto.getExName()));
        Assert.notNull(expert, "专家不存在");
        if (!expert.getExPassword().equals(SecureUtil.md5(expertLoginDto.getExPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(expert.getExId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        expertService.updateById(expert);
        return Result.succ(MapUtil.builder()
                .put("exId", expert.getExId())
                .put("exName", expert.getExName())
                .put("exEmail", expert.getExEmail())
                .put("exBio", expert.getExBio())
                .put("exDire", expert.getExDire())
                .put("exQualification", expert.getExQualification())
                .put("createdTime", expert.getCreatedTime())
                .map()
        );
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("用户已退出");
    }

    @RequiresAuthentication
    @PostMapping("/adminLogout")
    public Result adminLogout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("管理员已退出");
    }

    @RequiresAuthentication
    @PostMapping("/expertLogout")
    public Result expertLogout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("专家已退出");
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) {

        String encryptedPassword = SecureUtil.md5(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        user.setCreatedTime(new Date());
        String msg = userService.registerService(user);

        if (("SUCCESS").equals(msg)) {
            return Result.succ("注册成功");
        } else {
            return Result.fail(msg);
        }
    }

    @PostMapping("/adminRegister")
    public Result adminRegister(@Validated @RequestBody Admin admin) {

        String encryptedPassword = SecureUtil.md5(admin.getAdminPassword());
        admin.setAdminPassword(encryptedPassword);
        admin.setCreatedTime(new Date());
        String msg = adminService.registerService(admin);

        if (("SUCCESS").equals(msg)) {
            return Result.succ("注册成功");
        } else {
            return Result.fail(msg);
        }
    }

    @PostMapping("/expertRegister")
    public Result expertRegister(@Validated @RequestBody Expert expert) {

        String encryptedPassword = SecureUtil.md5(expert.getExPassword());
        expert.setExPassword(encryptedPassword);
        expert.setCreatedTime(new Date());
        String msg = expertService.registerService(expert);

        if (("SUCCESS").equals(msg)) {
            return Result.succ("注册成功");
        } else {
            return Result.fail(msg);
        }
    }
}
