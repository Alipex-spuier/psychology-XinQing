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
    @GetMapping("/index")
    public Result index() {
        return Result.succ(expertService.list());
    }

    @RequiresAuthentication
    @PostMapping("/index/{exId}")
    public Result index(@PathVariable Integer exId) {
        Expert expert = expertService.getById(exId);
        return Result.succ(expert);
    }

    @RequiresAuthentication
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
    public Result save(@RequestBody Expert expert){
        String password = SecureUtil.md5(expert.getExPassword());
        expert.setExPassword(password);
        return expertService.save(expert)?Result.succ(expert):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @DeleteMapping("/delete/{exId}")
    public Result delete(@PathVariable Integer exId) {
        return Result.succ(expertService.removeById(exId));
    }
}
