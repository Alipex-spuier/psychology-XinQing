package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Test;
import com.music.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/indexPage")
    @ApiOperation(value = "分页查询数据库中的测试 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":1}")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = testService.page(page);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @PostMapping("/indexPageByNameOrContent")
    @ApiOperation(value ="通过name(题目名)和content(题干)(模糊查询，二者中任一包含传入的关键词就可以被查到)分页查询数据库中的题目 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"word\":\"te\"\n" +
            "}}"
    )
    public Result indexPageByNameOrContent(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String word = (String)param.get("word");

        if(ObjectUtil.isEmpty(word.replace(" ",""))){
            return Result.fail("输入关键词为空！");
        }

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Test::getTestContent,word).or().like(Test::getTestName,word);

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

        if(ObjectUtil.isEmpty(belongingId)){
            return Result.fail("belongingId为空！");
        }

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Test::getBelongingId,belongingId);

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

        if(ObjectUtil.isEmpty(name.replace(" ",""))){
            return Result.fail("name为空！");
        }

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Test::getTestName,name);

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

        if(ObjectUtil.isEmpty(content.replace(" ",""))){
            return Result.fail("关键字为空！");
        }

        Page<Test> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Test> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Test::getTestContent,content);

        IPage result = testService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="更新测试题信息,testId必填，其他几个想改啥填啥 "+
            "{\"testId\":5,\n" +
            "\"belongingId\": 1,\n" +
            "\"testScore\":10\n" +
            "\"testName\": \"test5,更新测试\",\n" +
            "\"testContent\":\"题干信息\",\n" +
            "\"testChoA\":\"选项A\",\n" +
            "\"testChoB\":\"选项B\",\n" +
            "\"testChoC\":\"选项C\",\n" +
            "\"testChoD\":\"选项D\",\n" +
            "\"testproportionA\":\"A占比系数\",\n" +
            "\"testproportionB\":\"B占比系数\",\n" +
            "\"testproportionC\":\"C占比系数\",\n" +
            "\"testproportionD\":\"D占比系数\",\n" +
            "}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Test test){
        if(test.getTestId()==null || ObjectUtil.isEmpty(testService.getById(test.getTestId()))){
            return Result.fail("找不到testId");
        }
        if(test.getBelongingId()!=null && ObjectUtil.isEmpty(test.getBelongingId())){
            return Result.fail("belongingId不能为空");
        }
        testService.updateById(test);
        Test newTest= testService.getById(test.getTestId());
        return Result.succ(MapUtil.builder()
                .put("testId", newTest.getTestId())
                .put("belongingId", newTest.getBelongingId())
                .put("testName", newTest.getTestName())
                .put("testScore",newTest.getTestScore())
                .put("testContent",newTest.getTestContent())
                .put("testChoA",newTest.getTestChoA())
                .put("testChoB",newTest.getTestChoB())
                .put("testChoC",newTest.getTestChoC())
                .put("testChoD",newTest.getTestChoD())
                .put("testproportionA",newTest.getTestProportionA())
                .put("testproportionB",newTest.getTestProportionB())
                .put("testproportionC",newTest.getTestProportionC())
                .put("testproportionD",newTest.getTestProportionD())
                .map());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="设置题目所述题单 "+
            "[a,b](其中a为要处理的题目的testId，b为要设置为的belongingId,不用大括号)"
    )
    @PutMapping("/setBelongingId")
    public Result setBelongingId(@RequestBody Integer[] ids){
        if(ObjectUtil.isEmpty(testService.getById(ids[0]))){
            return Result.fail("找不到testId");
        }
        Test test = testService.getById(ids[0]);
        test.setBelongingId(ids[1]);
        return Result.succ(MapUtil.builder()
                .put("testId", test.getTestId())
                .put("belongingId", test.getBelongingId())
                .put("testName", test.getTestName())
                .put("testScore",test.getTestScore())
                .put("testContent",test.getTestContent())
                .put("testChoA",test.getTestChoA())
                .put("testChoB",test.getTestChoB())
                .put("testChoC",test.getTestChoC())
                .put("testChoD",test.getTestChoD())
                .put("testproportionA",test.getTestProportionA())
                .put("testproportionB",test.getTestProportionB())
                .put("testproportionC",test.getTestProportionC())
                .put("testproportionD",test.getTestProportionD())
                .map());
    }

    //@RequiresAuthentication

    @PostMapping("/getBelongingId")
    @ApiOperation(value ="得到题目所属题单 "+
            "a (a为要处理的题目的testId,不用大括号)"
    )
    public Result getBelongingId(@RequestBody Integer testId){
        if(ObjectUtil.isEmpty(testService.getById(testId))){
            return Result.fail("找不到testId");
        }
        Test test = testService.getById(testId);
        return Result.succ(test.getBelongingId());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="新建并保存一个题目，testId和belonging必填，其余选填 "+
            "{\"testId\":5,\n" +
            "\"belongingId\": 1,\n" +
            "\"testScore\":10\n" +
            "\"testName\": \"test5,更新测试\",\n" +
            "\"testContent\":\"题干信息\",\n" +
            "\"testChoA\":\"选项A\",\n" +
            "\"testChoB\":\"选项B\",\n" +
            "\"testChoC\":\"选项C\",\n" +
            "\"testChoD\":\"选项D\",\n" +
            "\"testproportionA\":\"A占比系数\",\n" +
            "\"testproportionB\":\"B占比系数\",\n" +
            "\"testproportionC\":\"C占比系数\",\n" +
            "\"testproportionD\":\"D占比系数\",\n" +
            "}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Test test){
        return testService.save(test)?Result.succ(test):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过testId删除一个题目 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{testId}")
    public Result delete(@PathVariable Integer testId) {
        if(ObjectUtil.isEmpty(testService.getById(testId))){
            return Result.succ("该条测试已不存在");
        }
        return Result.succ(testService.removeById(testId));
    }
}
