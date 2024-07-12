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
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
            "    \"pageNum\" :2" )
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
    @ApiOperation(value = "用于根据expertId搜索所有预约记录（已分页） "+
            "    \"pageSize\":1,\n" +
            "    \"pageNum\" :2" )
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
    @ApiOperation(value = "用于根据日期搜索所有预约记录（已分页） "+
            "    \"pageSize\":2,\n" +
            "    \"pageNum\":1,\n" +
            "    \"param\":{\n" +
            "        \"aptTime\":\"2024-07-10\"\n" +
            "    }" )
    @RequiresAuthentication
    @PostMapping("/indexPageByDay")
    public Result indexPageByDay(@RequestBody QueryPageParam query){
        Page<Appointment> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        String startTime = (String) query.getParam().get("aptTime");
        String endTime = startTime + " 23:59:59";
        SimpleDateFormat startTimeSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat endTimeSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = new Date();Date endDate = new Date();
        try{
            startDate = startTimeSdf.parse(startTime);
            endDate = endTimeSdf.parse(endTime);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(Appointment::getAptTime,startDate,endDate);
        IPage result = appointmentService.pageCC(page,lambdaQueryWrapper);

        return Result.succ(result.getRecords());
    }//根据expertId分页模糊查询显示
    @ApiOperation(value = "用于删除一条预约记录 "+
            "    \"aptId\":1" )
    @RequiresAuthentication
    @DeleteMapping("/delete/{aptId}")
    public Result Delete(@PathVariable Integer aptId){
        return ObjectUtil.isNotEmpty(appointmentService.getById(aptId))?Result.succ(appointmentService.removeById(aptId)):Result.fail("不存在这个aptId");
    }//删除
    @ApiOperation(value = "用于更新一条预约记录 aptId必填"+
            "    \"aptId\":1,\n" +
            "    \"userId\":1,\n" +
            "    \"expertId\":1,\n" +
            "    \"aptTime\":\"2024-07-01\"")
    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Appointment appointment){
        if(ObjectUtil.isEmpty(appointmentService.getById(appointment.getAptId())))
            return Result.fail("没有这个预约记录");
        if(ObjectUtil.isNotEmpty(appointment.getUserId())&&ObjectUtil.isEmpty(userService.getById(appointment.getUserId())))
            return Result.fail("不存在该用户");
        if(ObjectUtil.isNotEmpty(appointment.getExpertId())&&ObjectUtil.isEmpty(expertService.getById(appointment.getExpertId())))
            return  Result.fail("不存在该专家");
        appointmentService.updateById(appointment);
        Appointment newAppointment = appointmentService.getById(appointment.getAptId());
        return Result.succ(MapUtil.builder()
                .put("aptId",newAppointment.getAptId())
                .put("userId",newAppointment.getUserId())
                .put("expertId", newAppointment.getExpertId())
                .put("aptTime",newAppointment.getAptTime())
                .put("aptStatus", newAppointment.getAptStatus())
                .map());
    }//修改

    @ApiOperation(value = "用于添加一条预约记录 "+
            "    \"expertId\":2,\n" +
            "    \"aptTime\":\"2024-07-11T14:00:00\"")
    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Appointment appointment){
        appointment.setUserId(null);
        appointment.setAptStatus("N");
        return appointmentService.save(appointment)?Result.succ(appointment):Result.fail("保存失败！");
    }//放预约时间的时候需要把statue设置成N，即未被预约,userId设置为Null
    @ApiOperation(value = "用于用户预约一条记录 "+
            "     \"aptId\":10,\n" +
            "    \"expertId\":4,\n" +
            "    \"aptTime\":\"2024-07-11T14:00:00\"")
    @RequiresAuthentication
    @PostMapping("/appoint/{userId}")
    public Result appoint(@Validated @RequestBody Appointment appointment,@PathVariable Long userId){
        if (ObjectUtil.isEmpty(userService.getById(userId)))
            return Result.fail("没有这个用户");
        if("Y".equals(appointmentService.getById(appointment.getAptId()).getAptStatus()))
            return Result.fail("该预约已被预约");
        appointment.setUserId(userId);
        appointment.setAptStatus("Y");
        return appointmentService.updateById(appointment)?Result.succ(appointmentService.getById(appointment.getAptId())):Result.fail("预约失败");
    }

    @ApiOperation(value = "用于用户预约一条记录 "+
            " \"aptTime\":\"2024-07-11\"")
    @RequiresAuthentication
    @PostMapping("/accountByDay")
    public Result appointmentAccountByDay(@RequestBody HashMap param){
        if(ObjectUtil.isEmpty(param.get("aptTime"))||"".equals((String) param.get("aptTime")))
            return Result.fail("请传正确的时间");
        String aptTime = (String) param.get("aptTime");
        String endDay = aptTime+" 23:59:59";
        aptTime +=" 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();Date endDate = new Date();
        try{
            date = simpleDateFormat.parse(aptTime);
            endDate = simpleDateFormat.parse(endDay);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        LambdaQueryWrapper<Appointment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(Appointment::getAptTime,date,endDate);
        return Result.succ(appointmentService.accountByDay(lambdaQueryWrapper));
    }
}