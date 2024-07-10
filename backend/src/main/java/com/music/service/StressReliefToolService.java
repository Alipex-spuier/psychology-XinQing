package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.StressReliefTool;
import org.apache.ibatis.annotations.Param;

public interface StressReliefToolService extends IService<StressReliefTool> {
    IPage<StressReliefTool> pageCC(IPage<StressReliefTool> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
