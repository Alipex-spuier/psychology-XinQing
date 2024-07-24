package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
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
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value ="用户登录接口 "+
            "{\"username\": \"test1\",\n" +
            "\"password\": \"123\"}"
    )
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getUserId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        user.setLastLogin(new Date());
        userService.updateById(user);
        return Result.succ(MapUtil.builder()
                .put("userId", user.getUserId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .put("work", user.getWork())
                .put("country", user.getCountry())
                .put("age", user.getAge())
                .put("createdTime", user.getCreated())
                .put("lastLogin", user.getLastLogin())
                .map()
        );
    }

    @PostMapping("/adminLogin")
    @ApiOperation(value ="管理员登录接口 "+
            "{\"adminName\":\"test5\",\n" +
            "\"adminPassword\":\"123\"}"
    )
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
    @ApiOperation(value ="专家登录接口 "+
            "{\"exName\":\"test5\",\n" +
            "\"exPassword\":\"123\"}"
    )
    public Result expertLogin(@Validated @RequestBody ExpertLoginDto expertLoginDto, HttpServletResponse response) {
        Expert expert = expertService.getOne(new QueryWrapper<Expert>().eq("ex_name", expertLoginDto.getExName()));
        Assert.notNull(expert, "专家不存在");
        if (!expert.getExPassword().equals(SecureUtil.md5(expertLoginDto.getExPassword()))) {
            return Result.fail("密码不正确");
        }
        if (expert.getChecked()==0){
            return Result.fail("未通过认证！");
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
                .put("avatar", expert.getAvatar())
                .put("checked", expert.getChecked())
                .put("createdTime", expert.getCreatedTime())
                .map()
        );
    }

    @RequiresAuthentication
    @ApiOperation(value ="用户退出登录接口 "+
            "{不传参，使用token确定用户}"
    )
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("用户已退出");
    }

    @RequiresAuthentication
    @ApiOperation(value ="管理员退出登录接口 "+
            "{不传参，使用token确定管理员}"
    )
    @PostMapping("/adminLogout")
    public Result adminLogout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("管理员已退出");
    }

    @RequiresAuthentication
    @ApiOperation(value ="专家退出登录接口 "+
            "{不传参，使用token确定专家}"
    )
    @PostMapping("/expertLogout")
    public Result expertLogout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("专家已退出");
    }

    @PostMapping("/register")
    @ApiOperation(value ="用户注册接口 "+
            "{\"username\": \"test1\",\n" +
            "\"password\": \"123\",\n" +
            "\"email\":\"123@qq.com\"" +
            "这三个必须，其他属性非必须，可通过update更新}"
    )
    public Result register(@Validated @RequestBody User user) {

        String encryptedPassword = SecureUtil.md5(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setCreated(new Date());
        String msg = userService.registerService(user);

        if (("SUCCESS").equals(msg)) {
            return Result.succ("注册成功");
        } else {
            return Result.fail(msg);
        }
    }

    @PostMapping("/adminRegister")
    @ApiOperation(value ="管理员注册接口 "+
            "{\"adminName\":\"test5\",\n" +
            "\"adminPassword\":\"123\",\n" +
            "\"adminEmail\":\"123@qq.com\"" +
            "这三个必须，其他属性非必须，可通过update更新}"
    )
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
    @ApiOperation(value ="专家注册接口 "+
            "{\"exName\":\"test5\",\n" +
            "\"exPassword\":\"123\",\n" +
            "\"exEmail\":\"123@qq.com\"" +
            "\"exBio\":\"exBio测试\",\n" +
            "\"exDire\":\"exDire测试\",\n" +
            "\"exQualification\":\"exQualification测试\"\n" +
            "这些必须，其他属性非必须，可通过update更新}"
    )
    public Result expertRegister(@Validated @RequestBody Expert expert) {
        if(expert.getExBio()==null || ObjectUtil.isEmpty(expert.getExBio().replace(" ",""))){
            return Result.fail("请填写专家简介！");
        }
        if(expert.getExDire()==null || ObjectUtil.isEmpty(expert.getExDire().replace(" ",""))){
            return Result.fail("请填写主治方向！");
        }
        if(expert.getExQualification()==null || ObjectUtil.isEmpty(expert.getExQualification().replace(" ",""))){
            return Result.fail("请填写专家职称！");
        }
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
