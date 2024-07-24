package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
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
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/testResult")
public class TestResultController {

    private final TestResultService testResultService;
    private final UserService userService;

    @Autowired
    public TestResultController(TestResultService testResultService, UserService userService) {
        this.testResultService = testResultService;
        this.userService = userService;
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
        if (ObjectUtil.isEmpty(testResult)) {
            return Result.fail("不存在该测试结果");
        }
        return Result.succ(testResult);
    }

    //@RequiresAuthentication
    @ApiOperation(value ="分页查询数据库中的测试结果 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1" +
            "}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();

        Page<TestResult> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = testResultService.page(page);
        return Result.succ(result.getRecords(), result.getTotal());
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过userId分页查询数据库中的测试结果 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"userId\":2\n" +
            "}}"
    )
    @PostMapping("/indexPageByUser")
    public Result indexPageByUser(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer userId = (Integer)param.get("userId");

        if (userId == null || ObjectUtil.isEmpty(userId)) {
            return Result.fail("userId不存在");
        }

        Page<TestResult> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<TestResult> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(TestResult::getUserId,userId);

        IPage result = testResultService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords(), result.getTotal());
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
        if(testResult.getResId()==null || ObjectUtil.isEmpty(testResultService.getById(testResult.getResId()))){
            return Result.fail("找不到resId");
        }
        if(testResult.getUserId()!=null && ObjectUtil.isEmpty(testResult.getUserId())){
            return Result.fail("userId不能为空");
        }
        if(testResult.getBelongingId()!=null && ObjectUtil.isEmpty(testResult.getBelongingId())){
            return Result.fail("belongingId不能为空");
        }
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
            "{\"userId\": 3,\n" +
            "\"resResult\": \"您的测试结果为………………\",\n" +
            "\"belongingId\":1}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody @Valid TestResult testResult){
        if(testResult.getUserId()!=null && ObjectUtil.isEmpty(userService.getById(testResult.getUserId()))){
            return Result.fail("不存在该用户");
        }
        testResult.setResTime(new Date());
        return testResultService.save(testResult)?Result.succ(testResult):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过resId删除一个用户 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{resId}")
    public Result delete(@PathVariable Integer resId) {
        if(ObjectUtil.isEmpty(testResultService.getById(resId))){
            return Result.succ("该条记录已不存在");
        }
        return Result.succ(testResultService.removeById(resId));
    }

}
