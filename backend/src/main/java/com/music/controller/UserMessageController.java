package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.UserMessage;
import com.music.service.AdminService;
import com.music.service.UserMessageService;
import com.music.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/userMessage")
public class UserMessageController {
    @Autowired
    UserMessageService userMessageService;
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @GetMapping("/index")
    public Result index() {
        return Result.succ(userMessageService.list());
    }
    //查询
    @GetMapping("/index/{mesId}")
    public Result getOneByMesId(@PathVariable Integer mesId){
        return Result.succ(userMessageService.getById(mesId));
    }//根据mesId查询

    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        Page<UserMessage> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = userMessageService.page(page);

        return Result.succ(result.getRecords());
    }//分页模糊查询显示

    @RequiresAuthentication
    @PostMapping("/indexPage/user/{userId}")
    public Result indexPageByUserId(@RequestBody QueryPageParam query,@PathVariable Integer userId){
        Page<UserMessage> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<UserMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(UserMessage::getUserId,userId);

        IPage result = userMessageService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据userId分页模糊查询显示
    @RequiresAuthentication
    @PostMapping("/indexPage/admin/{adminId}")
    public Result indexPageByExpertId(@RequestBody QueryPageParam query,@PathVariable Integer adminId){
        Page<UserMessage> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<UserMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(UserMessage::getAdminId,adminId);

        IPage result = userMessageService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据adminId分页模糊查询显示

    @DeleteMapping("/delete/{mesId}")
    public Result delete(@PathVariable Integer mesId){
        return Result.succ(userMessageService.removeById(mesId));
    }//删除

    @PutMapping("/save")
    public Result save(@RequestBody UserMessage userMessage){
        userMessage.setMesTime(new Date());
        return userMessageService.save(userMessage)?Result.succ(userMessage):Result.fail("保存失败！");
    }//增加
    @PostMapping("/update")
    public Result update(@RequestBody UserMessage userMessage){
        if(ObjectUtil.isEmpty(userMessageService.getById(userMessage.getMesId())))
            return Result.fail("没有这个消息");
        if(ObjectUtil.isEmpty(userService.getById(userMessage.getUserId())))
            return Result.fail("没有这个用户");
        if(ObjectUtil.isEmpty(adminService.getById(userMessage.getAdminId())))
            return Result.fail("没有这个管理员");
        userMessageService.updateById(userMessage);
        UserMessage newUserMessage = userMessageService.getById(userMessage.getMesId());
        return Result.succ(MapUtil.builder()
                .put("mesId",newUserMessage.getMesId())
                .put("userId", newUserMessage.getUserId())
                .put("adminId",newUserMessage.getAdminId())
                .put("mesContent", newUserMessage.getMesContent())
                .put("mesTime",newUserMessage.getMesTime())
                .map());
    }//修改
}
