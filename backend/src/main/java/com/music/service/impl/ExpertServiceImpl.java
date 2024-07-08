package com.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Expert;
import com.music.mapper.ExpertMapper;
import com.music.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements ExpertService {
    @Autowired
    ExpertMapper expertMapper;

    @Override
    public String registerService(Expert expert) {
        Expert expertSearch = expertMapper.searchByExName(expert.getExName());
        if (expertSearch == null) {
            if ("".equals(expert.getExPassword())) {
                return "请输入密码";
            } else if ("".equals(expert.getExName())) {
                return "请输入专家昵称";
            } else {
                expertMapper.insert(expert);
                return "SUCCESS";
            }
        }
        return "专家已存在";
    }
}
