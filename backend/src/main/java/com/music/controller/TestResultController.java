package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.TestResult;
import com.music.entity.User;
import com.music.service.TestResultService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/testresult")
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
    @ApiOperation(value ="列出数据库中userId为{userId}的管理员 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{testResultId}")
    public Result index(@PathVariable Integer testResultId) {
        TestResult testResult = testResultService.getById(testResultId);
        return Result.succ(testResult);
    }

}
