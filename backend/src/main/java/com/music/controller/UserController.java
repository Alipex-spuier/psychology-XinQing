package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.User;
import com.music.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@Valid @RequestBody User user){
        return Result.succ(userService.updateById(user));
    }
    @RequiresAuthentication
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.succ(userService.removeById(id));
    }
}