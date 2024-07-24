package com.music.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Article;
import com.music.entity.Comment;
import com.music.service.ArticleService;
import com.music.service.CommentService;
import com.music.service.ExpertService;
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final ExpertService expertService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService, ExpertService expertService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.expertService = expertService;
    }

    @GetMapping("/index")
    @ApiOperation(value ="列出数据库中所有资讯文章 "+
            "{不传参}"
    )
    public Result index() {
        return Result.succ(articleService.list());
    }

    @PostMapping("/index/{artId}")
    @ApiOperation(value ="列出数据库中artId为{artId}的资讯文章"+
            "{参数通过url传入}"
    )
    public Result index(@PathVariable Integer artId) {
        Article article = articleService.getById(artId);
        if (ObjectUtil.isEmpty(article)) {
            return Result.fail("不存在该测试结果");
        }
        return Result.succ(article);
    }

    @GetMapping("/indexPage")
    @ApiOperation(value = "分页查询数据库中的资讯文章 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1}")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();

        Page<Article> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = articleService.page(page);
        return Result.succ(result.getRecords(),result.getTotal());
    }

    @PostMapping("/indexPageByTitle")
    @ApiOperation(value = "通过artTitle分页查询数据库中的资讯文章 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"artTitle\":\"文章标题关键字\"\n" +
            "}}")
    public Result indexPageByTitle(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("artTitle");

        if(name == null || ObjectUtil.isEmpty(name.replace(" ",""))){
            return Result.fail("输入标题为空！");
        }

        Page<Article> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Article::getArtTitle,name);

        IPage result = articleService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords(), result.getTotal());
    }

    @ApiOperation(value = "通过artAuthor分页查询数据库中的资讯文章 "+
            "\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"artAuthor\":4\n" +
            "}")
    @PostMapping("/indexPageByAuthor")
    public Result indexPageByAuthor(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer name = (Integer) param.get("artAuthor");

        if(name == null || ObjectUtil.isEmpty(name)){
            return Result.fail("作者Id为空！");
        }

        Page<Article> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Article::getArtAuthor,name);

        IPage result = articleService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords(), result.getTotal());
    }

    @ApiOperation(value ="更新资讯文章信息,artId必填，根据artId来确定改哪个，其他几个想改啥填啥 "+
            "{\"artId\":5,\n" +
            "\"artAuthor\":4,\n" +
            "\"artTitle\":\"文章标题信息\"\n" +
            "\"artContent\":\"文章正文\"\n" +
            "\"artPic\":\"文章封面图片url\"\n"+
            "}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Article article){
        if(article.getArtId()==null || ObjectUtil.isEmpty(articleService.getById(article.getArtId()))){
            return Result.fail("找不到artId");
        }
        if(article.getArtAuthor()!=null && ObjectUtil.isEmpty(article.getArtAuthor())){
            return Result.fail("artAuthor不能为空");
        }
        if(article.getArtTitle()!=null && ObjectUtil.isEmpty(article.getArtTitle().replace(" ",""))){
            return Result.fail("artTitle不能为空");
        }
        if(article.getArtPic()!=null && ObjectUtil.isEmpty(article.getArtPic().replace(" ",""))){
            return Result.fail("artPic不能为空");
        }
        article.setArtTime(new Date().getTime());
        articleService.updateById(article);
        Article newArticle = articleService.getById(article.getArtId());
        return Result.succ(MapUtil.builder()
                .put("artId", newArticle.getArtId())
                .put("artAuthor", newArticle.getArtAuthor())
                .put("artTitle", newArticle.getArtTitle())
                .put("artContent", newArticle.getArtContent())
                .put("artPic", newArticle.getArtPic())
                .put("artTime", newArticle.getArtTime())
                .map());
    }

    @PostMapping("/save")
    @ApiOperation(value ="新建并保存一个文章，artAuthor、artTitle、artPic必填，artContent选填 "+
            "{\"artAuthor\":4,\n" +
            "\"artTitle\":\"文章标题信息\"\n" +
            "\"artContent\":\"文章正文\"\n" +
            "\"artPic\":\"文章封面图片url\"\n"+
            "}"
    )
    public Result save(@RequestBody @Valid Article article){
        if(article.getArtAuthor()!=null && ObjectUtil.isEmpty(expertService.getById(article.getArtAuthor()))){
            return Result.fail("此artAuthor并不存在");
        }
        article.setArtTime(new Date().getTime());
        return articleService.save(article)?Result.succ(articleService.getById(article.getArtId())):Result.fail("保存失败！");
    }

    //@RequiresAuthentication
    @ApiOperation(value ="通过artId删除一个文章 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{artId}")
    public Result delete(@PathVariable Integer artId) {
        if(ObjectUtil.isEmpty(articleService.getById(artId))){
            return Result.succ("该条记录已不存在");
        }

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<Comment>();
        lambdaQueryWrapper.eq(Comment::getArtId,artId);

        commentService.remove(lambdaQueryWrapper);
        return Result.succ(articleService.removeById(artId));
    }

}
