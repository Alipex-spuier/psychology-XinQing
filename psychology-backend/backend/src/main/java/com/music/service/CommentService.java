package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Comment;
import com.music.entity.User;

public interface CommentService extends IService<Comment> {
    IPage pageCC(IPage<Comment> page, Wrapper wrapper);
}
