package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Appointment;
import com.music.service.AppointmentService;
import com.music.service.ConsultationLogService;
import com.music.service.ExpertService;
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @Autowired
    ExpertService expertService;

    @Autowired
    ConsultationLogService consultationLogService;

    @ApiOperation(value = "用于搜索所有预约记录 "+
            "不需要传参数" )
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        return Result.succ(appointmentService.list());
    }//全查找
    @ApiOperation(value = "用于搜索所有预约记录（已分页） "+
            "    \"pageSize\":1,\n" +
            "    \"pageNum\" :2" )
    @RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        Page<Appointment> page = new Page<>();

        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = appointmentService.page(page);

        return Result.succ(result.getRecords());
    }//分页模糊查询显示
    @ApiOperation(value = "用于根据userId搜索所有预约记录（已分页） "+
            "    \"pageSize\":1,\n" +
            "    \"pageNum\" :2" +
            "   \"param\":{\n" +
            "    \"userId\":1\n"+
            "   }")
    @RequiresAuthentication
    @PostMapping("/indexPageByUserId")
    public Result indexPageByUserId(@RequestBody QueryPageParam query){
        Page<Appointment> page = new Page<>();
        if(ObjectUtil.isEmpty(query.getParam().get("userId")))
            return Result.fail("请传入userId");
        if(ObjectUtil.isEmpty(userService.getById((Integer)query.getParam().get("userId"))))
            return Result.fail("没有这个userId");
        Integer userId = (Integer)query.getParam().get("userId");
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Appointment::getUserId,userId);

        IPage result = appointmentService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据userId分页模糊查询显示
    @ApiOperation(value = "用于根据expertId搜索所有预约记录（已分页） "+
            "    \"pageSize\":1,\n" +
            "    \"pageNum\" :2"+
            "   \"param\":{\n" +
            "    \"expertId\":2\n" +
            "   }")
    @RequiresAuthentication
    @PostMapping("/indexPageByExpertId")
    public Result indexPageByExpertId(@RequestBody QueryPageParam query){
        Page<Appointment> page = new Page<>();
        if(ObjectUtil.isEmpty(query.getParam().get("expertId")))
            return Result.fail("请传入expertId");
        if(ObjectUtil.isEmpty(userService.getById((Integer)query.getParam().get("expertId"))))
            return Result.fail("没有这个expertId");
        Integer expertId = (Integer)query.getParam().get("expertId");
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Appointment::getExpertId,expertId);

        IPage result = appointmentService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据expertId分页模糊查询显示
    @ApiOperation(value = "用于删除一条预约记录 apt对应的consultationLog也会随之删除"+
            "    \"aptId\":1" )
    @RequiresAuthentication
    @DeleteMapping("/delete/{aptId}")
    public Result Delete(@PathVariable Integer aptId){
        if(ObjectUtil.isEmpty(appointmentService.getById(aptId)))
            return Result.fail("没有该预约记录");
        if(ObjectUtil.isNotEmpty(consultationLogService.getByAptId(aptId)))
            consultationLogService.removeById(consultationLogService.getByAptId(aptId));
        return appointmentService.removeById(aptId)?Result.succ("删除成功",true):Result.fail("删除失败");
    }//删除
    @ApiOperation(value = "用于更新一条预约记录 aptId必填"+
            "    \"aptId\":1,\n" +
            "    \"userId\":1,\n" +
            "    \"expertId\":1,\n" +
            "    \"aptTime\":1720748470000")
    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Appointment appointment){
        if(ObjectUtil.isEmpty(appointmentService.getById(appointment.getAptId())))
            return Result.fail("没有这个预约记录");
        if(ObjectUtil.isNotEmpty(appointment.getUserId())&&ObjectUtil.isEmpty(userService.getById(appointment.getUserId())))
            return Result.fail("不存在该用户");
        if(ObjectUtil.isNotEmpty(appointment.getExpertId())&&ObjectUtil.isEmpty(expertService.getById(appointment.getExpertId())))
            return  Result.fail("不存在该专家");
        if(appointment.getAptTime().equals(appointmentService.searchAllByExpertId(appointment.getExpertId()).getAptTime())&&!appointment.getAptId().equals(appointmentService.searchAllByExpertId(appointment.getExpertId()).getAptId()))
            return Result.fail("该专家该时间段有预约");
        appointmentService.updateById(appointment);
        Appointment newAppointment = appointmentService.getById(appointment.getAptId());
        return Result.succ(MapUtil.builder()
                .put("aptId",newAppointment.getAptId())
                .put("userId",newAppointment.getUserId())
                .put("expertId", newAppointment.getExpertId())
                .put("aptTime",newAppointment.getAptTime())
                .map());
    }//修改

    @ApiOperation(value = "用于添加一条预约记录 "+
            "    \"aptTime\":\"2024-07-11T14:00:00\"")
    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Appointment appointment){
        appointment.setUserId(null);
        return appointmentService.save(appointment)?Result.succ(appointment):Result.fail("保存失败！");
    }//放预约时间的时候需要把statue设置成N，即未被预约,userId设置为Null
    @ApiOperation(value = "用于用户预约一条记录 时间戳为数字(Long型)"+
            "    \"userId\":1,\n" +
            "    \"expertId\":4,\n" +
            "    \"aptTime\":1720748470000")
    @RequiresAuthentication
    @PostMapping("/appoint")
    public Result appoint(@Validated @RequestBody Appointment appointment){
        if (ObjectUtil.isEmpty(userService.getById(appointment.getUserId())))
            return Result.fail("不存在该用户");
        if(ObjectUtil.isEmpty(expertService.getById(appointment.getExpertId())))
            return Result.fail("不存在该专家");
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Appointment::getExpertId,appointment.getExpertId()).and((wrapper)->wrapper.eq(Appointment::getAptTime,appointment.getAptTime()));
        if(ObjectUtil.isEmpty(appointmentService.getOneByExpertIdAndAptTime(lambdaQueryWrapper))) {
            appointmentService.save(appointment);
            return Result.succ("预约成功",true);
        }
        return Result.fail("时间段已被预约");
    }
    @ApiOperation(value = "用于查询某专家某时间段有没有预约记录 时间戳为数字(Long型)"+
            "  \"expertId\":4,\n" +
            "    \"aptTime\":1720748470000")
    @RequiresAuthentication
    @PostMapping("/searchOneByExpertIdAndAptTime")
    public Result searchOneByExpertIdAndAptTime(@Validated @RequestBody Appointment appointment){
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Appointment::getExpertId,appointment.getExpertId()).and((wrapper)->wrapper.eq(Appointment::getAptTime,appointment.getAptTime()));
        if(ObjectUtil.isEmpty(appointmentService.getOneByExpertIdAndAptTime(lambdaQueryWrapper))) {
            return Result.succ("该专家该时间段没有预约",true);
        }
            return Result.fail("该专家该时间段已被预约",false);
    }

    @ApiOperation(value = "用于查询某天的预约总数 时间戳精度为毫秒，当天任意时刻时间戳即可 时间戳为数字(Long型)"+
            " \"aptTime\":1720748470000")
    @RequiresAuthentication
    @PostMapping("/accountByDay")
    public Result appointmentAccountByDay(@RequestBody HashMap param){
        if(ObjectUtil.isEmpty(param.get("aptTime")))
            return Result.fail("请传正确的时间");
        Long aptTime = (Long)param.get("aptTime");
        Date date = new Date(aptTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatTime = format.format(date);
        String dayBegin = formatTime+" 00:00:00";
        String dayEnd = formatTime +" 23:59:59";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startOfDay = new Date(); Date endOfDay = new Date();
        try {
            startOfDay = format1.parse(dayBegin);
            endOfDay = format1.parse(dayEnd);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Long beginDay = startOfDay.getTime();
        Long endDay = endOfDay.getTime();
        System.out.println(beginDay);
        System.out.println(endDay);
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(Appointment::getAptTime,beginDay,endDay);
        return Result.succ(appointmentService.accountByDay(lambdaQueryWrapper));
    }
}