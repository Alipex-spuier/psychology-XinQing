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
    @GetMapping("/musics")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 140);
        IPage pageData= musicService.page(page, new QueryWrapper<Music>().orderByDesc("details_id"));
        return Result.succ(pageData);
    }
}
