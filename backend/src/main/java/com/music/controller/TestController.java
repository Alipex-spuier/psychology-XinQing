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
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value ="列出数据库中所有test "+
            "{不传参}"
    )
    public Result index() {
        return Result.succ(testService.list());
    }

    //@RequiresAuthentication
    @PostMapping("/index/{testId}")
    @ApiOperation(value ="列出数据库中testId为{testId}的测试题 "+
            "{参数通过url传入}"
    )
    public Result index(@PathVariable Integer testId) {
        Test test = testService.getById(testId);
        return Result.succ(test);
    }

    //@RequiresAuthentication
    @PostMapping("/indexPage")
    @ApiOperation(value ="通过name(题目名)和content(题干)(模糊查询，二者中任一包含传入的关键词就可以被查到)分页查询数据库中的题目 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"word\":\"te\"\n" +
            "}}"
    )
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
    @ApiOperation(value = "通过belongingId(所属题单)分页查询数据库中的题目 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"belongingId\":1\n" +
            "}")
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
    @ApiOperation(value = "通过name(题目名)分页查询数据库中的题目 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"name\":\"te\"\n" +
            "}")
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
    @ApiOperation(value = "通过content(题干内容)分页查询数据库中的题目 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"content\":\"te\"\n" +
            "}")
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
    @ApiOperation(value ="更新测试题信息,testId必填，其他几个想改啥填啥 "+
            "{\"exId\":5,\n" +
            "\"testName\": \"test5,更新测试\",\n" +
            "\"belongingId\": 1,\n" +
            "\"testContent\":\"题干信息\",\n" +
            "\"testChoA\":\"选项A\",\n" +
            "\"testChoB\":\"选项B\",\n" +
            "\"testChoC\":\"选项C\",\n" +
            "\"testChoD\":\"选项D\",\n" +
            "\"testAnswer\":\"测试答案\",\n" +
            "\"testScore\":10\n" +
            "}"
    )
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
    @ApiOperation(value ="设置题目所述题单 "+
            "[a,b](其中a为要处理的题目的testId，b为要设置为的belongingId,不用大括号)"
    )
    @PutMapping("/setBelongingId")
    public Result setBelongingId(@RequestBody Integer[] ids){
        Test test = testService.getById(ids[0]);
        test.setBelongingId(ids[1]);
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

    @PostMapping("/getBelongingId")
    @ApiOperation(value ="得到题目所属题单 "+
            "a (a为要处理的题目的testId,不用大括号)"
    )
    public Result getBelongingId(@RequestBody Integer testId){
        Test test = testService.getById(testId);
        return Result.succ(test.getBelongingId());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="新建并保存一个题目，testId和testName必填，其余选填 "+
            "{\"testId\":1\n" +
            "\"testName\":\"test4\",\n" +
            "\"belongingId\":5,\n" +
            "\"testChoA\":\"选项A\",\n" +
            "\"testChoB\":\"选项B\",\n" +
            "\"testChoC\":\"选项C\",\n" +
            "\"testChoD\":\"\",\n" +
            "\"testContent\":\"这是一道三个选项测试题，正确答案为C\",\n" +
            "\"testAnswer\":\"C\",\n" +
            "\"testScore\":25}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody Test test){
        return testService.save(test)?Result.succ(test):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过testId删除一个题目 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{testId}")
    public Result delete(@PathVariable Integer testId) {
        return Result.succ(testService.removeById(testId));
    }
}
