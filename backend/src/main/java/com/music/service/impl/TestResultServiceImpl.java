package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.TestResult;
import com.music.mapper.TestResultMapper;
import com.music.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultServiceImpl extends ServiceImpl<TestResultMapper, TestResult> implements TestResultService {
    @Autowired
    TestResultMapper testResultMapper;
    @Override
    public IPage<TestResult> pageCC(Page<TestResult> page, Wrapper wrapper) {
        return testResultMapper.pageCC(page, wrapper);
    }
}
