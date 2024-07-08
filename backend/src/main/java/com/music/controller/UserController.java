package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.music.common.lang.Result;
import com.music.entity.User;
import com.music.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequiresAuthentication
    @PostMapping("/index/{userId}")
    public Result index(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        return Result.succ(user);
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        User newUser = userService.getById(user.getUserId());
        return Result.succ(MapUtil.builder()
                .put("userId", newUser.getUserId())
                .put("userName", newUser.getUserName())
                .put("userEmail", newUser.getUserEmail())
                .put("userIntro", newUser.getUserIntro())
                .put("createdTime", newUser.getCreatedTime())
                .map());
    }
    @RequiresAuthentication
    @DeleteMapping("/delete/{userId}")
    public Result delete(@PathVariable Integer userId) {
        return Result.succ(userService.removeById(userId));
    }
}