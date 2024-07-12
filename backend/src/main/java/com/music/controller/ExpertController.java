package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Expert;
import com.music.entity.User;
import com.music.service.ExpertService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/expert")
public class ExpertController {

    private final ExpertService expertService;

    @Autowired
    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中所有expert "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(expertService.list());
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中exId为{exId}的专家 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{exId}")
    public Result index(@PathVariable Integer exId) {
        Expert expert = expertService.getById(exId);
        return Result.succ(expert);
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过名字（模糊查询）分页查询数据库中的专家 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"name\":\"te\"\n" +
            "}}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Expert> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Expert> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Expert::getExName,name);
        }else{
            return Result.fail("name为空！");
        }

        IPage result = expertService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="更新专家信息,exId必填，其他几个想改啥填啥 "+
            "{\"exId\":5,\n" +
            "\"exName\": \"test5,更新测试\",\n" +
            "\"exPassword\": \"123\",\n" +
            "\"exEmail\":\"123@qq.com\",\n" +
            "\"exBio\":\"exBio测试\",\n" +
            "\"exDire\":\"exDire测试\",\n" +
            "\"exQualification\":\"exQualification测试\"\n" +
            "}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Expert expert){
        String password = SecureUtil.md5(expert.getExPassword());
        expert.setExPassword(password);
        expertService.updateById(expert);
        Expert newExpert = expertService.getById(expert.getExId());
        return Result.succ(MapUtil.builder()
                .put("exId", newExpert.getExId())
                .put("exName", newExpert.getExName())
                .put("exEmail", newExpert.getExEmail())
                .put("exBio", newExpert.getExBio())
                .put("exDire", newExpert.getExDire())
                .put("exQualification", newExpert.getExQualification())
                .put("createdTime", newExpert.getCreatedTime())
                .map());
    }

    @RequiresAuthentication
    @PostMapping("/save")
    @ApiOperation(value ="新建并保存一个用户，以下两个必填，其他选填 "+
            "{\"exName\":\"test6\",\n" +
            "\"exPassword\":\"123\"}"
    )
    public Result save(@RequestBody Expert expert){
        String password = SecureUtil.md5(expert.getExPassword());
        expert.setExPassword(password);
        return expertService.save(expert)?Result.succ(expert):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过exId删除一个专家 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{exId}")
    public Result delete(@PathVariable Integer exId) {
        return Result.succ(expertService.removeById(exId));
    }
}
