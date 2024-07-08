package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Admin;
import com.music.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        return Result.succ(adminService.list());
    }

    @RequiresAuthentication
    @PostMapping("/index/{adminId}")
    public Result index(@PathVariable Integer adminId) {
        Admin admin = adminService.getById(adminId);
        return Result.succ(admin);
    }

    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Admin> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Admin::getAdminName,name);
        }else{
            return Result.fail("name为空！");
        }

        IPage result = adminService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.updateById(admin);
        Admin newAdmin = adminService.getById(admin.getAdminId());
        return Result.succ(MapUtil.builder()
                .put("exId", newAdmin.getAdminId())
                .put("exName", newAdmin.getAdminName())
                .put("exEmail", newAdmin.getAdminEmail())
                .put("createdTime", newAdmin.getCreatedTime())
                .map());
    }

    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        return adminService.save(admin)?Result.succ(admin):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @DeleteMapping("/delete/{adminId}")
    public Result delete(@PathVariable Integer adminId) {
        return Result.succ(adminService.removeById(adminId));
    }

}
