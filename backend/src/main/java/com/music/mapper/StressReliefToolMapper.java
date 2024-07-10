package com.music.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.music.entity.StressReliefTool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StressReliefToolMapper extends BaseMapper<StressReliefTool> {
    IPage<StressReliefTool> pageCC(IPage<StressReliefTool> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
