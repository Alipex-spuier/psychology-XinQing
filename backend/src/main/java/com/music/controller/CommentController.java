package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Comment;
import com.music.service.ArticleService;
import com.music.service.CommentService;
import com.music.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, ArticleService articleService) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中所有评论 "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(commentService.list());
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中commentsId为{commentsId}的评论 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{commentsId}")
    public Result index(@PathVariable Integer commentsId) {
        Comment comment = commentService.getById(commentsId);
        return Result.succ(comment);
    }

    @RequiresAuthentication
    @ApiOperation(value ="(模糊查询)分页查询数据库中的评论 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1" +
            "}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){

        Page<Comment> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result = commentService.page(page);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过userId(用户id)(模糊查询)分页查询数据库中的评论 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"userId\":2\n" +
            "}}"
    )
    @PostMapping("/indexPageByUserId")
    public Result indexPageByUserId(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer id = (Integer) param.get("userId");

        if(id == null || ObjectUtil.isEmpty(id)){
            return Result.fail("id为空！");
        }

        Page<Comment> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getUserId,id);

        IPage result = commentService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过artId(文章id)(模糊查询)分页查询数据库中的评论 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"artId\":2\n" +
            "}}"
    )
    @PostMapping("/indexPageByArtId")
    public Result indexPageByArtId(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer id = (Integer) param.get("artId");

        if (id == null || ObjectUtil.isEmpty(id)){
            return Result.fail("id为空！");
        }

        Page<Comment> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getArtId,id);

        IPage result = commentService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="更新评论信息,commentsId必填 "+
            "{\"commentsId\":1,\n" +
            "\"userId\":3,\n" +
            "\"artId\":2,\n" +
            "\"commentsContent\":\"评论内容1,更新修改\"}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Comment comment){
        if(comment.getCommentsId()==null || ObjectUtil.isEmpty(commentService.getById(comment.getArtId()))){
            return Result.fail("找不到commentsId");
        }
        if(comment.getUserId()!=null && ObjectUtil.isEmpty(comment.getUserId())){
            return Result.fail("userId不能为空");
        }
        if(comment.getArtId()!=null && ObjectUtil.isEmpty(comment.getArtId())){
            return Result.fail("artId不能为空");
        }
        if(comment.getCommentsContent()!=null && ObjectUtil.isEmpty(comment.getCommentsContent().replace(" ",""))){
            return Result.fail("commentsContent不能为空");
        }
        comment.setCommentsTime(new Date().getTime());
        commentService.updateById(comment);
        Comment newComment = commentService.getById(comment.getCommentsId());
        return Result.succ(MapUtil.builder()
                .put("commentId", newComment.getCommentsId())
                .put("userId", newComment.getUserId())
                .put("artId", newComment.getArtId())
                .put("commentContent", newComment.getCommentsContent())
                .put("commentTime", newComment.getCommentsTime())
                .map());
    }

    @RequiresAuthentication
    @ApiOperation(value ="新建并保存一个评论，两个Id必填 "+
            "{\"userId\":3,\n" +
            "\"artId\":2,\n" +
            "\"commentsContent\":\"评论内容1,更新修改\"}"
    )
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Comment comment){
        if(comment.getUserId()!=null && ObjectUtil.isEmpty(userService.getById(comment.getUserId()))){
            return Result.fail("此userId并不存在");
        }
        if(comment.getArtId()!=null && ObjectUtil.isEmpty(articleService.getById(comment.getArtId()))){
            return Result.fail("此artId并不存在");
        }
        comment.setCommentsTime(new Date().getTime());
        return commentService.save(comment)?Result.succ(commentService.getById(comment.getCommentsId())):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过commentsId删除一个题目 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{commentsId}")
    public Result delete(@PathVariable Integer commentsId) {
        if(ObjectUtil.isEmpty(commentService.getById(commentsId))){
            return Result.succ("该条记录已不存在");
        }
        return Result.succ(commentService.removeById(commentsId));
    }
}
