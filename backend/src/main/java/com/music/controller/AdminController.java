package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.User;
import com.music.service.AdminService;
import com.music.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @RequiresAuthentication
    @PostMapping("/index/{userId}")
    public Result indexById(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        return Result.succ(user);
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        return Result.succ(userService.list());
    }

    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getUsername,name);
        }else{
            return Result.fail("name为空！");
        }

        IPage result = userService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return userService.save(user)?Result.succ(user):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        User newUser = userService.getById(user.getUserId());
        return Result.succ(MapUtil.builder()
                .put("userId", newUser.getUserId())
                .put("username", newUser.getUsername())
                .put("avatar", newUser.getAvatar())
                .put("userEmail", newUser.getEmail())
                .put("password", newUser.getPassword())
                .put("work", newUser.getWork())
                .put("country", newUser.getCountry())
                .put("age", newUser.getAge())
                .put("createdTime", newUser.getCreated())
                .put("lastLogin", newUser.getLastLogin())
                .map());
    }

    @RequiresAuthentication
    @DeleteMapping("/delete/{userId}")
    public Result delete(@PathVariable Integer userId) {
        return Result.succ(userService.removeById(userId));
    }


}
