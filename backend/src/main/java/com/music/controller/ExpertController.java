package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.music.common.lang.Result;
import com.music.entity.Expert;
import com.music.service.ExpertService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expert")
public class ExpertController {

    private final ExpertService expertService;

    @Autowired
    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @RequiresAuthentication
    @PostMapping("/index/{exId}")
    public Result index(@PathVariable Integer exId) {
        Expert expert = expertService.getById(exId);
        return Result.succ(expert);
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Expert expert){
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
    @DeleteMapping("/delete/{exId}")
    public Result delete(@PathVariable Integer exId) {
        return Result.succ(expertService.removeById(exId));
    }
}
