package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.TestResult;
import com.music.entity.User;
import com.music.service.TestResultService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/testResult")
public class TestResultController {

    private final TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    //@RequiresAuthentication
    @ApiOperation(value ="列出数据库中所有testResult "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(testResultService.list());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="列出数据库中testResultId为{testResultId}的测试结果 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{testResultId}")
    public Result index(@PathVariable Integer testResultId) {
        TestResult testResult = testResultService.getById(testResultId);
        return Result.succ(testResult);
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过userId分页查询数据库中的测试结果 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"userId\":2\n" +
            "}}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer userId = (Integer)param.get("userId");

        Page<TestResult> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<TestResult> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(userId!=null && !"null".equals(userId)){
            lambdaQueryWrapper.eq(TestResult::getUserId,userId);
        }else{
            return Result.fail("userId为空！");
        }

        IPage result = testResultService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="更新一条测试结果,testResultId必填，其他几个想改啥填啥 "+
            "{\"resId\":5,\n" +
            "\"userId\": 3,\n" +
            "\"resResult\": \"您的测试结果为………………\",\n" +
            "\"belongingId\":1}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody TestResult testResult){
        testResultService.updateById(testResult);
        TestResult newResult= testResultService.getById(testResult.getResId());
        return Result.succ(MapUtil.builder()
                .put("resId", newResult.getResId())
                .put("userId", newResult.getUserId())
                .put("belongingId", newResult.getBelongingId())
                .put("resResult", newResult.getResResult())
                .map());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="新建并保存一个测试结果，所有内容均必填 "+
            "{\"resId\":5,\n" +
            "\"userId\": 3,\n" +
            "\"resResult\": \"您的测试结果为………………\",\n" +
            "\"belongingId\":1}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody TestResult testResult){
        testResult.setResTime(new Date());
        return testResultService.save(testResult)?Result.succ(testResult):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过resId删除一个用户 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{resId}")
    public Result delete(@PathVariable Integer resId) {
        return Result.succ(testResultService.removeById(resId));
    }

}
