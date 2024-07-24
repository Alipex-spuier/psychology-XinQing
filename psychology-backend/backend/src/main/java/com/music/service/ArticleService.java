package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Article;

public interface ArticleService extends IService<Article> {
    IPage pageCC(IPage<Article> page, Wrapper wrapper);

}
