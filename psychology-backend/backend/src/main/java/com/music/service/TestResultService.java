package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.TestResult;

public interface TestResultService extends IService<TestResult> {
    IPage<TestResult> pageCC(Page<TestResult> page, Wrapper wrapper);
}
