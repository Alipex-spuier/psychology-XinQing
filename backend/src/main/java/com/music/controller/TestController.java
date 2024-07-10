package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Test;
import com.music.service.TestService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    //@RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        return Result.succ(testService.list());
    }

    //@RequiresAuthentication
    @PostMapping("/index/{testId}")
    public Result index(@PathVariable Integer testId) {
        Test test = testService.getById(testId);
        return Result.succ(test);
    }

    //@RequiresAuthentication
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String word = (String)param.get("word");

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(word) && !"null".equals(word)){
            lambdaQueryWrapper.like(Test::getTestContent,word).or().like(Test::getTestName,word);
        }else{
            return Result.fail("输入为空！");
        }

        IPage result = testService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @PostMapping("/indexPageById")
    public Result indexPageById(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer belongingId = (Integer)param.get("belongingId");

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(belongingId!=null && !"null".equals(belongingId)){
            lambdaQueryWrapper.eq(Test::getBelongingId,belongingId);
        }else{
            return Result.fail("输入id为空！");
        }

        IPage result = testService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @PostMapping("/indexPageByName")
    public Result indexPageByName(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Test::getTestName,name);
        }else{
            return Result.fail("name为空！");
        }

        IPage result = testService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @PostMapping("/indexPageByContent")
    public Result indexPageByContent(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String content = (String)param.get("content");

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(content) && !"null".equals(content)){
            lambdaQueryWrapper.like(Test::getTestContent,content);
        }else{
            return Result.fail("关键字为空！");
        }

        IPage result = testService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Test test){
        testService.updateById(test);
        Test newTest= testService.getById(test.getTestId());
        return Result.succ(MapUtil.builder()
                .put("testId", newTest.getTestId())
                .put("testName", newTest.getTestName())
                .put("belongingId", newTest.getBelongingId())
                .put("testContent",newTest.getTestContent())
                .put("testChoA",newTest.getTestChoA())
                .put("testChoB",newTest.getTestChoB())
                .put("testChoC",newTest.getTestChoC())
                .put("testChoD",newTest.getTestChoD())
                .put("testAnswer",newTest.getTestAnswer())
                .put("testScore",newTest.getTestScore())
                .map());
    }

    //@RequiresAuthentication
    @PutMapping("/setBelongingId")
    public Result setBelongingId(@RequestParam("testId") Integer testId,@RequestParam("belongingId") Integer belongingId){
        Test test = testService.getById(testId);
        test.setBelongingId(belongingId);
        return Result.succ(MapUtil.builder()
                .put("testId", test.getTestId())
                .put("testName", test.getTestName())
                .put("belongingId", test.getBelongingId())
                .put("testContent",test.getTestContent())
                .put("testChA",test.getTestChoA())
                .put("testChoB",test.getTestChoB())
                .put("testChoC",test.getTestChoC())
                .put("testChoD",test.getTestChoD())
                .put("testAnswer",test.getTestAnswer())
                .put("testScore",test.getTestScore())
                .map());
    }

    //@RequiresAuthentication
    @PostMapping("/save")
    public Result save(@RequestBody Test test){
        return testService.save(test)?Result.succ(test):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @DeleteMapping("/delete/{testId}")
    public Result delete(@PathVariable Integer testId) {
        return Result.succ(testService.removeById(testId));
    }
}
