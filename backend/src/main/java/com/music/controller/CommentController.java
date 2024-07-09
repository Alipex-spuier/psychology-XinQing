package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Comment;
import com.music.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        return Result.succ(commentService.list());
    }

    @RequiresAuthentication
    @PostMapping("/index/{commentsId}")
    public Result index(@PathVariable Integer commentsId) {
        Comment comment = commentService.getById(commentsId);
        return Result.succ(comment);
    }

    @RequiresAuthentication
    @PostMapping("/indexPageByUserId")
    public Result indexPageByUserId(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer id = (Integer) param.get("userId");

        Page<Comment> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(id!=null && !"null".equals(id)){
            lambdaQueryWrapper.eq(Comment::getUserId,id);
        }else{
            return Result.fail("id为空！");
        }

        IPage result = commentService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @PostMapping("/indexPageByArtId")
    public Result indexPageByArtId(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        Integer id = (Integer) param.get("artId");

        Page<Comment> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(id!=null && !"null".equals(id)){
            lambdaQueryWrapper.eq(Comment::getArtId,id);
        }else{
            return Result.fail("id为空！");
        }

        IPage result = commentService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @PutMapping("/update")
    public Result update(@RequestBody Comment comment){
        comment.setCommentsTime(new Date());
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
    @PostMapping("/save")
    public Result save(@RequestBody Comment comment){
        comment.setCommentsTime(new Date());
        return commentService.save(comment)?Result.succ(comment):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @DeleteMapping("/delete/{commentsId}")
    public Result delete(@PathVariable Integer commentsId) {
        return Result.succ(commentService.removeById(commentsId));
    }
}
