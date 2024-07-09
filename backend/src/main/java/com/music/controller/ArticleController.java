package com.music.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Article;
import com.music.service.ArticleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public Result index() {
        return Result.succ(articleService.list());
    }

    @PostMapping("/index/{artId}")
    public Result index(@PathVariable Integer artId) {
        Article article = articleService.getById(artId);
        return Result.succ(article);
    }

    @PostMapping("/indexPageByTitle")
    public Result indexPageByTitle(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("artTitle");

        Page<Article> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Article::getArtTitle,name);
        }else{
            return Result.fail("标题为空！");
        }

        IPage result = articleService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @PostMapping("/indexPageByAuthor")
    public Result indexPageByAuthor(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer name = (Integer) param.get("artAuthor");

        Page<Article> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!"null".equals(name.toString())){
            lambdaQueryWrapper.like(Article::getArtAuthor,name);
        }else{
            return Result.fail("作者为空！");
        }

        IPage result = articleService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @PutMapping("/update")
    public Result update(@RequestBody Article article){
        article.setArtTime(new Date());
        articleService.updateById(article);
        Article newArticle = articleService.getById(article.getArtId());
        return Result.succ(MapUtil.builder()
                .put("artId", newArticle.getArtId())
                .put("artAuthor", newArticle.getArtAuthor())
                .put("artTitle", newArticle.getArtTitle())
                .put("artPic", newArticle.getArtPic())
                .put("artTime", newArticle.getArtTime())
                .map());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Article article){
        article.setArtTime(new Date());
        return articleService.save(article)?Result.succ(article):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @DeleteMapping("/delete/{artId}")
    public Result delete(@PathVariable Integer artId) {
        return Result.succ(articleService.removeById(artId));
    }
}
