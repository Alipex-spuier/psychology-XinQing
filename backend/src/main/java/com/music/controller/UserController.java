package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Appointment;
import com.music.entity.User;
import com.music.service.AppointmentService;
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中所有user "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(userService.list());
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中userId为{userId}的管理员 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{userId}")
    public Result index(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if(ObjectUtil.isEmpty(user))
            return Result.fail("没有这个用户");
        return Result.succ(user);
    }

    @RequiresAuthentication
    @ApiOperation(value ="（模糊查询）分页查询数据库中的用户 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n}"
    )
    @GetMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = userService.page(page);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过名字（模糊查询）分页查询数据库中的用户 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"name\":\"te\"\n" +
            "}}"
    )
    @PostMapping("/indexPageByName")
    public Result indexPageByName(@RequestBody QueryPageParam query){
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
    @ApiOperation(value ="更新用户信息,userId必填，其他几个想改啥填啥 "+
            "{\"userId\":5,\n" +
            "\"username\": \"test5,更新测试\",\n" +
            "\"password\": \"123\",\n" +
            "\"email\":\"123@qq.com\",\n" +
            "\"work\":\"工作测试\",\n" +
            "\"country\":\"国家测试\",\n" +
            "\"age\":11,\n" +
            "\"avatar\":\"avatar测试\"}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        if(user.getUserId()==null || ObjectUtil.isEmpty(user.getUserId())){
            return Result.fail("userId不能为空！");
        }
        if(user.getUsername()!=null){
            if(ObjectUtil.isEmpty(user.getUsername().replace(" ",""))){
                return Result.fail("userName不能为空！");
            }else {
                if(ObjectUtil.isNotEmpty(userService.searchByUsername(user.getUsername()))
                        &&!user.getUserId().equals(userService.searchByUsername(user.getUsername()).getUserId()))
                    return Result.fail("昵称已存在");
            }
        }
        if(ObjectUtil.isNotEmpty(user.getPassword())) {
            String password = SecureUtil.md5(user.getPassword());
            user.setPassword(password);
        }
        if(user.getEmail()!=null && ObjectUtil.isEmpty(user.getEmail())){
            return Result.fail("email不能为空！");
        }
        if(user.getAge()!=null){
            if(user.getAge()>150){
                return Result.fail("年龄不大于150！");
            } else if (user.getAge()<0) {
                return Result.fail("年龄不小于0！");
            }
        }
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
    @ApiOperation(value ="新建并保存一个用户，以下两个必填，其他选填 "+
            "{\"username\":\"test6\",\n" +
            "\"password\":\"123\"}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody @Valid User user){
        if(user.getAge()!=null){
            if(user.getAge()>150){
                return Result.fail("年龄不大于150！");
            } else if (user.getAge()<0) {
                return Result.fail("年龄不小于0！");
            }
        }
        String password = SecureUtil.md5(user.getPassword());
        user.setPassword(password);
        return userService.save(user)?Result.succ(user):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过userId删除一个用户 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{userId}")
    public Result delete(@PathVariable Integer userId) {
        if(ObjectUtil.isEmpty(userService.getById(userId))){
            return Result.fail("该条测试已不存在");
        }
        return userService.removeById(userId)?Result.succ(userService.removeById(userId)):Result.fail("对不起，有外键，请别删！");
    }

}