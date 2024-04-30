package com.music.service.impl;

import com.music.entity.Blog;
import com.music.mapper.BlogMapper;
import com.music.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
