package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.ConsultationLog;
import com.music.service.AppointmentService;
import com.music.service.ConsultationLogService;
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/consultationLog")
public class ConsultationLogController {


    private final ConsultationLogService consultationLogService;

    private final AppointmentService appointmentService;

    @Autowired
    public ConsultationLogController(ConsultationLogService consultationLogService,AppointmentService appointmentService){
        this.consultationLogService = consultationLogService;
        this.appointmentService = appointmentService;
    }
    @ApiOperation(value = "用于查找所有记录 "+
            "不传参数")
    @RequiresAuthentication
    @GetMapping("index")
    public Result index(){
        return Result.succ(consultationLogService.list());
    }//全查询
    @ApiOperation(value = "用于根据logId查找某一条记录 "+
            "\"logId\": *")
    @RequiresAuthentication
    @PostMapping("index/{logId}")
    public Result indexByLogId(@PathVariable Integer logId){
        return ObjectUtil.isEmpty(consultationLogService.getById(logId))?Result.fail("没有该记录"):Result.succ(consultationLogService.getById(logId));
    }//查询单条记录
    @ApiOperation(value = "用于分页全查询 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":2")
    @RequiresAuthentication
    @PostMapping("indexPage")
    public Result indexPage(@RequestBody QueryPageParam queryPageParam){

        Page<ConsultationLog> page = new Page<>();

        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        IPage<ConsultationLog> result = consultationLogService.page(page);
        return Result.succ(result.getRecords());
    }//分页查询

    @ApiOperation(value = "用于从起始时间到当前时间的记录查询（已做分页处理） "+
            "   \"pageSize\":2,\n" +
            "   \"pageNum\":2,\n" +
            "   \"param\":{\n" +
            "    \"logTime\":\"2024-07-10")
    @RequiresAuthentication
    @PostMapping("indexPageByStartTime")
    public Result indexPageByStartTime(@RequestBody QueryPageParam queryPageParam){

        Page<ConsultationLog> page = new Page<>();

        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        Long logTime = (Long) queryPageParam.getParam().get("logTime");
        if(ObjectUtil.isEmpty(logTime))
            return Result.fail("错误的时间");

        LambdaQueryWrapper<ConsultationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(ConsultationLog::getLogTime,logTime);

        IPage<ConsultationLog> result = consultationLogService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }//根据起始时间分页查询
    @ApiOperation(value = "用于根据时间范围查询记录（已做分页处理） "+
            " \"pageSize\":2,\n" +
            "   \"pageNum\":2,\n" +
            "   \"param\":{\n" +
            "        \"logTime\":1720748470000,\n" +
            "        \"endTime\":1720748470001,\n"+
            "}")
    @RequiresAuthentication
    @PostMapping("indexPageByRangeTime")
    public Result indexPageByRangeTime(@RequestBody QueryPageParam queryPageParam){

        Page<ConsultationLog> page = new Page<>();

        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        Long startTime = (Long)queryPageParam.getParam().get("startTime");
        Long endTime = (Long)queryPageParam.getParam().get("endTime");
        if(ObjectUtil.isEmpty(startTime)||ObjectUtil.isEmpty(endTime))
            return Result.fail("错误的时间");

        LambdaQueryWrapper<ConsultationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(ConsultationLog::getLogTime,startTime,endTime);

        IPage<ConsultationLog> result = consultationLogService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }//分页查询

    @ApiOperation(value = "用于增加记录 logTime要是没传后端会自动赋当前时间"+
            "    \"aptId\":1   " +
            "    \"logTime\":1720748470000"+
            "    \"logContent\":\"test1\"")
    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@Validated @RequestBody ConsultationLog consultationLog){

        if(ObjectUtil.isEmpty(appointmentService.getById(consultationLog.getAptId())))
            return Result.fail("没有这个预约记录");
        if(ObjectUtil.isEmpty(consultationLog.getLogTime()))
            consultationLog.setLogTime(new Date().getTime());
        if(ObjectUtil.isEmpty(consultationLog.getLogContent()))
            return  Result.fail("请输入内容");

        if(!consultationLogService.save(consultationLog))
            return Result.fail("保存失败");
        return Result.succ(consultationLogService.getById(consultationLog.getLogId()));
    }//增加

    @ApiOperation(value = "用于删除记录 "+
            "\"logId\":1")
    @RequiresAuthentication
    @DeleteMapping("/delete/{logId}")
    public Result delete(@PathVariable Integer logId){
        return ObjectUtil.isNotEmpty(consultationLogService.getById(logId))?Result.succ(consultationLogService.removeById(logId)):Result.fail("没有这个日志记录");
    }//删除

    @ApiOperation(value = "用于根据logId更新记录 logId必填"+
            "    \"logId\":1,\n" +
            "    \"aptId\":1,\n" +
            "    \"logTime\":1720748470000"+
            "    \"logContent\":\"test1\"")
    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody ConsultationLog consultationLog){

        if(ObjectUtil.isEmpty(consultationLogService.getById(consultationLog.getLogId())))
            return Result.fail("没有这个日志记录");
        if(ObjectUtil.isNotEmpty(consultationLog.getAptId())&&ObjectUtil.isEmpty(appointmentService.getById(consultationLog.getAptId())))
            return Result.fail("不存在该预约记录");

        consultationLogService.updateById(consultationLog);
        ConsultationLog newConsultationLog = consultationLogService.getById(consultationLog.getLogId());
        return Result.succ(MapUtil.builder()
                .put("logId",newConsultationLog.getLogId())
                .put("aptId",newConsultationLog.getAptId())
                .put("logContent",newConsultationLog.getLogContent())
                .put("logTime",newConsultationLog.getLogTime())
                .map());
    }//更新
}
