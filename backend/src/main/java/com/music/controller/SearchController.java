package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Article;
import com.music.entity.Expert;
import com.music.service.ArticleService;
import com.music.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {
    @Autowired
    ExpertService expertService;
    @Autowired
    ArticleService articleService;

    @PostMapping("")
    public Result Search(@RequestBody QueryPageParam queryPageParam){
        String dire = (String) queryPageParam.getParam().get("dire");
        Page<Expert> pageExpert = new Page<>();
        pageExpert.setSize(queryPageParam.getPageSize());
        pageExpert.setCurrent(queryPageParam.getPageNum());
        LambdaQueryWrapper<Expert> lambdaQueryWrapperExpert = new LambdaQueryWrapper<>();
        lambdaQueryWrapperExpert.like(Expert::getExDire,dire);
        IPage expertIpage = expertService.pageCC(pageExpert,lambdaQueryWrapperExpert);
        List<Expert> expertList = expertIpage.getRecords();//Expert的分页处理

        Page<Article> pageArticle = new Page<>();
        pageArticle.setCurrent(queryPageParam.getPageNum());
        pageArticle.setSize(queryPageParam.getPageSize());
        LambdaQueryWrapper<Article> lambdaQueryWrapperArticle = new LambdaQueryWrapper<>();
        lambdaQueryWrapperArticle.like(Article::getArtTitle,dire).or().like(Article::getArtContent,dire);
        IPage articleIPage = articleService.pageCC(pageArticle,lambdaQueryWrapperArticle);
        List<Article> articleList = articleIPage.getRecords();//Article的分页处理
        return Result.succ(MapUtil.builder()
                .put("expertList",expertList)
                .put("articleList",articleList)
                .map());
    }
}
