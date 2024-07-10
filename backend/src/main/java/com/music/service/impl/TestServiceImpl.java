package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Test;
import com.music.mapper.TestMapper;
import com.music.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public IPage<Test> pageCC(Page<Test> page, Wrapper wrapper) {
        return testMapper.pageCC(page, wrapper);
    }
}
