package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Appointment;
import com.music.service.AppointmentService;
import com.music.service.ExpertService;
import com.music.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @Autowired
    ExpertService expertService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        return Result.succ(appointmentService.list());
    }//全查找

    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        Page<Appointment> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = appointmentService.page(page);

        return Result.succ(result.getRecords());
    }//分页模糊查询显示

    @RequiresAuthentication
    @PostMapping("/indexPage/user/{userId}")
    public Result indexPageByUserId(@RequestBody QueryPageParam query,@PathVariable Integer userId){
        Page<Appointment> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Appointment::getUserId,userId);

        IPage result = appointmentService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据userId分页模糊查询显示
    @RequiresAuthentication
    @PostMapping("/indexPage/expert/{expertId}")
    public Result indexPageByExpertId(@RequestBody QueryPageParam query,@PathVariable Integer expertId){
        Page<Appointment> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Appointment::getUserId,expertId);

        IPage result = appointmentService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据expertId分页模糊查询显示

    @RequiresAuthentication
    @DeleteMapping("/delete/{aptId}")
    public Result Delete(@PathVariable Integer aptId){
        return Result.succ(appointmentService.removeById(aptId));
    }//删除

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Appointment appointment){
        if(ObjectUtil.isEmpty(appointmentService.getById(appointment.getAptId())))
            return Result.fail("没有这个预约记录");
        if(ObjectUtil.isEmpty(userService.getById(appointment.getUserId())))
            return Result.fail("不存在该用户");
        if(ObjectUtil.isEmpty(expertService.getById(appointment.getExpertId())))
            return  Result.fail("不存在该专家");
        appointmentService.updateById(appointment);
        Appointment newAppointment = appointmentService.getById(appointment.getAptId());
        return Result.succ(MapUtil.builder()
                .put("userId",newAppointment.getUserId())
                .put("expertId", newAppointment.getExpertId())
                .put("aptTime",newAppointment.getAptTime())
                .put("aptStatus", newAppointment.getAptStatus())
                .map());
    }//修改

    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@RequestBody Appointment appointment){
        appointment.setAptStatus("N");
        return appointmentService.save(appointment)?Result.succ(appointment):Result.fail("保存失败！");
    }//第一次保存预约的时候需要把statue设置成N，即未预约
}
