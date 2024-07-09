package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Article;
import com.music.mapper.ArticleMapper;
import com.music.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public IPage pageCC(IPage<Article> page, Wrapper wrapper) {
        return articleMapper.pageCC(page,wrapper);
    }

}
