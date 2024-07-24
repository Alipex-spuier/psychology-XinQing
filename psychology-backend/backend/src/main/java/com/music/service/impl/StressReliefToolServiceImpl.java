package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.StressReliefTool;
import com.music.mapper.StressReliefToolMapper;
import com.music.service.StressReliefToolService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StressReliefToolServiceImpl extends ServiceImpl<StressReliefToolMapper, StressReliefTool> implements StressReliefToolService {
   @Autowired
   StressReliefToolMapper stressReliefToolMapper;
    @Override
    public IPage<StressReliefTool> pageCC(IPage<StressReliefTool> page, @Param(Constants.WRAPPER) Wrapper wrapper){
        return stressReliefToolMapper.pageCC(page,wrapper);
    }
}
