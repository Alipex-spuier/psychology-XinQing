package com.music.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.Utils.ShiroUtil;
import com.music.common.lang.Result;

import com.music.entity.Music;
import com.music.service.MusicService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class MusicController {
    @Autowired
    MusicService musicService;
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData= musicService.page(page, new QueryWrapper<Music>().orderByDesc("created"));
        return Result.succ(pageData);
    }
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name="id")Long id) {
        Music music = musicService.getById(id);
        Assert.notNull(music, "该博客已被删除");
        return Result.succ(music);
    }
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Music music) {
        Music temp = null;
        if (music.getId() != null) {
            temp = musicService.getById(music.getId());
            Assert.isTrue(temp.getUserId().longValue()== ShiroUtil.getProfile().getId().longValue(),"你没有权限编辑");
        } else {
            temp = new Music();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(music, temp, "id", "userId", "created", "status");
        musicService.saveOrUpdate(temp);
        return Result.succ(null);
    }
    @RequiresAuthentication
    @PostMapping("/delBlog/{id}")
    public Result del(@PathVariable Long id){
        boolean temp = musicService.removeById(id);
        if(temp==true){
            return Result.succ("文章删除成功");
        }else{
            return Result.fail("文章删除失败");
        }
    }
}
