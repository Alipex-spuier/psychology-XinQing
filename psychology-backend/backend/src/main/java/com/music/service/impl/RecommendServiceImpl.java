package com.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Recommend;
import com.music.mapper.RecommendMapper;
import com.music.service.RecommendService;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

}
