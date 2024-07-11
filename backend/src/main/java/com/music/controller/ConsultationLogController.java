package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.ConsultationLog;
import com.music.service.AppointmentService;
import com.music.service.ConsultationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/consultationLog")
public class ConsultationLogController {
    @Autowired
    ConsultationLogService consultationLogService;
    @Autowired
    AppointmentService appointmentService;
    @ApiOperation(value = "用于查找所有记录 "+
            "不传参数")
    @GetMapping("index")
    public Result index(){
        return Result.succ(consultationLogService.list());
    }//全查询
    @ApiOperation(value = "用于根据logId查找某一条记录 "+
            "\"logId\": *")
    @PostMapping("index/{logId}")
    public Result indexByLogId(@PathVariable Integer logId){
        return ObjectUtil.isEmpty(consultationLogService.getById(logId))?Result.fail("没有该记录"):Result.succ(consultationLogService.getById(logId));
    }//查询单条记录
    @ApiOperation(value = "用于分页全查询 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":2")
    @PostMapping("indexPage")
    public Result indexPage(@RequestBody QueryPageParam queryPageParam){
        Page<ConsultationLog> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
        IPage<ConsultationLog> result = consultationLogService.page(page);
        return Result.succ(result.getRecords());
    }//分页查询
    @ApiOperation(value = "用于根据起始时间查询记录（已做分页处理） "+
            "   \"pageSize\":2,\n" +
            "   \"pageNum\":2,\n" +
            "   \"param\":{\n" +
            "    \"startTime\":\"2024-07-10\"\n" +
            "   }" + "日期传年月日\"yyyy-MM-dd\"格式")
    @PostMapping("indexPageByStartTime")
    public Result indexPageByStartTime(@RequestBody QueryPageParam queryPageParam){
        Page<ConsultationLog> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        String startTime = (String) queryPageParam.getParam().get("startTime");
        if("".equals(startTime))
            return Result.fail("错误的时间");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(startTime);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        LambdaQueryWrapper<ConsultationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(ConsultationLog::getLogTime,date,new Date());
        IPage<ConsultationLog> result = consultationLogService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }//根据起始时间分页查询
    @ApiOperation(value = "用于根据时间范围查询记录（已做分页处理） "+
            " \"pageSize\":2,\n" +
            "   \"pageNum\":2,\n" +
            "   \"param\":{\n" +
            "        \"startTime\":\"2024-07-10\",\n" +
            "        \"endTime\":\"2024-07-11\"\n" +
            "   }" + "日期传年月日\"yyyy-MM-dd\"格式")
    @PostMapping("indexPageByRangeTime")
    public Result indexPageByRangeTime(@RequestBody QueryPageParam queryPageParam){
        Page<ConsultationLog> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        String startTime = (String) queryPageParam.getParam().get("startTime");
        String endTime = (String) queryPageParam.getParam().get("endTime");
        if("".equals(startTime)||"".equals(endTime))
            return Result.fail("错误的时间");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = sdf.parse(startTime);
            endDate = sdf.parse(endTime);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        LambdaQueryWrapper<ConsultationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(ConsultationLog::getLogTime,startDate,endDate);
        IPage<ConsultationLog> result = consultationLogService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }//分页查询
    @ApiOperation(value = "用于增加记录 "+
            " \"aptId\":1,\n" +
            "    \"logContent\":\"test1\"")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody ConsultationLog consultationLog){
        if(ObjectUtil.isEmpty(appointmentService.getById(consultationLog.getAptId())))
            return Result.fail("没有这个预约记录");
        consultationLog.setLogTime(new Date());
        return consultationLogService.save(consultationLog)?Result.succ(consultationLog):Result.fail("保存失败！");
    }//增加
    @ApiOperation(value = "用于删除记录 "+
            "\"logId\":1")
    @DeleteMapping("/delete/{logId}")
    public Result delete(@PathVariable Integer logId){
        return ObjectUtil.isNotEmpty(consultationLogService.getById(logId))?Result.succ(consultationLogService.removeById(logId)):Result.fail("没有这个日志记录");
    }//删除
    @ApiOperation(value = "用于根据logId更新记录 logId必填"+
            "    \"logId\":1,\n" +
            "    \"aptId\":1,\n" +
            "    \"logContent\":\"test1\"")
    @PutMapping("/update")
    public Result update(@RequestBody ConsultationLog consultationLog){
        if(ObjectUtil.isEmpty(consultationLogService.getById(consultationLog.getLogId())))
            return Result.fail("没有这个日志记录");
        if(ObjectUtil.isNotEmpty(consultationLog.getAptId())&&ObjectUtil.isEmpty(appointmentService.getById(consultationLog.getAptId())))
            return Result.fail("不存在该预约记录");
        consultationLog.setLogTime(new Date());
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
