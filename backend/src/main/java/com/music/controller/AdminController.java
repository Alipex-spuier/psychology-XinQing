package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Admin;
import com.music.service.AdminService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value ="列出数据库中所有admin "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(adminService.list());
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中adminId为{adminId}的管理员 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{adminId}")
    public Result index(@PathVariable Integer adminId) {
        Admin admin = adminService.getById(adminId);
        if(ObjectUtil.isEmpty(admin))
            return Result.fail("没有这个管理员");
        return Result.succ(admin);
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过名字（模糊查询）分页查询数据库中的管理员 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"name\":\"te\"\n" +
            "}}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        if(name == null || ObjectUtil.isEmpty(name.replace(" ",""))){
            return Result.fail("输入name为空！");
        }

        Page<Admin> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Admin::getAdminName,name);

        IPage result = adminService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="更新管理员信息,adminId必填，其他三个想改啥填啥 "+
            "{\"adminId\":1\n" +
            "\"adminName\":\"test6\",\n" +
            "\"adminPassword\":\"123\",\n" +
            "\"adminEmail\":\"123@qq.com\"}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        String password = SecureUtil.md5(admin.getAdminPassword());
        admin.setAdminPassword(password);
        adminService.updateById(admin);
        Admin newAdmin = adminService.getById(admin.getAdminId());
        return Result.succ(MapUtil.builder()
                .put("adminId", newAdmin.getAdminId())
                .put("adminName", newAdmin.getAdminName())
                .put("adminEmail", newAdmin.getAdminEmail())
                .put("createdTime", newAdmin.getCreatedTime())
                .map());
    }

    @RequiresAuthentication
    @ApiOperation(value ="新建并保存一个管理员，以下两个必填，其他选填 "+
            "{\"adminName\":\"test6\",\n" +
            "\"adminPassword\":\"123\"}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        String password = SecureUtil.md5(admin.getAdminPassword());
        admin.setAdminPassword(password);
        return adminService.save(admin)?Result.succ(admin):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过adminId删除一个管理员 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{adminId}")
    public Result delete(@PathVariable Integer adminId) {
        return Result.succ(adminService.removeById(adminId));
    }

}
