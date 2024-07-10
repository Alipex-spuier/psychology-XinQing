package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.ConsultationLog;
import com.music.service.ConsultationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/consultationLog")
public class ConsultationLogController {
    @Autowired
    ConsultationLogService consultationLogService;

    @GetMapping("index")
    public Result index(){
        return Result.succ(consultationLogService.list());
    }//全查询
    @PostMapping("index/{logId}")
    public Result indexByLogId(@PathVariable Integer logId){
        return ObjectUtil.isEmpty(consultationLogService.getById(logId))?Result.fail("没有该记录"):Result.succ(consultationLogService.getById(logId));
    }//查询单条记录
    @PostMapping("indexPage")
    public Result indexPage(@RequestBody QueryPageParam queryPageParam){
        Page<ConsultationLog> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        String partContent = (String) queryPageParam.getParam().get("logContent");
        Date logTime = (Date) queryPageParam.getParam().get("logTime");

        LambdaQueryWrapper<ConsultationLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(ConsultationLog::getLogContent,partContent).or().like(ConsultationLog::getLogTime,logTime);
        IPage<ConsultationLog> result = consultationLogService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }//分页查询

    @PostMapping("/save")
    public Result save(@RequestBody ConsultationLog consultationLog){
        consultationLog.setLogTime(new Date());
        return consultationLogService.save(consultationLog)?Result.succ(consultationLog):Result.fail("保存失败！");
    }//增加
    @DeleteMapping("/delete/{logId}")
    public Result delete(@PathVariable Integer logId){
        return Result.succ(consultationLogService.removeById(logId));
    }//删除

    @PutMapping("/update")
    public Result update(@RequestBody ConsultationLog consultationLog){
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
