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
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    @ApiOperation(value = "用于userService的全查询 "+
            "不需要传参数")
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        return Result.succ(userMessageService.list());
    }
    //查询
    @ApiOperation(value = "用于单独查询某一条记录 "+
            " \"mesId\":* ")
    @RequiresAuthentication
    @GetMapping("/index/{mesId}")
    public Result getOneByMesId(@PathVariable Integer mesId){
        return Result.succ(userMessageService.getById(mesId));
    }//根据mesId查询

    @ApiOperation(value = "用于分页查询 "+
            "\"pageSize\":1,\n" +
            "    \"pageNum\":1")
    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        Page<UserMessage> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = userMessageService.page(page);

        return Result.succ(result.getRecords());
    }//分页模糊查询显示

    @ApiOperation(value = "用于根据userId的分页查询（查询一个用户的所有message）"+
            "\"pageSize\":*,\n" +
            "   \"pageNum\":*,\n" +
            "   \"param\":{\n" +
            "       \"userId\": *\n" +
            "   }")
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
    @ApiOperation(value = "用于根据adminId的分页查询（查询一个管理员的所有message）"+
            "   \"pageSize\":2,\n" +
            "   \"pageNum\":1,\n" +
            "   \"param\":{\n" +
            "       \"adminId\": *\n" +
            "   }")
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
    @ApiOperation(value = "用于删除一条记录"+
            "\"mesId\": *")
    @RequiresAuthentication
    @DeleteMapping("/delete/{mesId}")
    public Result delete(@PathVariable Integer mesId){
        return ObjectUtil.isNotEmpty(userMessageService.getById(mesId))?Result.succ(userMessageService.removeById(mesId)):Result.fail("没有这个用户留言");
    }//删除

    @ApiOperation(value = "用于添加一条userMessage"+
            "\"userId\": *")
    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@Validated @RequestBody UserMessage userMessage){
        if(ObjectUtil.isEmpty(userService.getById(userMessage.getUserId())))
            return Result.fail("没有这个用户");
        if(ObjectUtil.isEmpty(adminService.getById(userMessage.getAdminId())))
            return Result.fail("没有这个管理员");
        userMessage.setMesTime(new Date());
        return userMessageService.save(userMessage)?Result.succ(userMessage):Result.fail("保存失败！");
    }//增加
    @ApiOperation(value = "用于根据mesId更新一条记录"+
            "\"userId\":1,\n" +
            "    \"adminId\":1,\n" +
            "    \"mesConten\":\"test1\"")
    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody UserMessage userMessage){
        if(ObjectUtil.isEmpty(userMessageService.getById(userMessage.getMesId())))
            return Result.fail("没有这个消息");
        if(ObjectUtil.isEmpty(userService.getById(userMessage.getUserId())))
            return Result.fail("没有这个用户");
        if(ObjectUtil.isEmpty(adminService.getById(userMessage.getAdminId())))
            return Result.fail("没有这个管理员");
        userMessage.setMesTime(new Date());
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
