package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.ConsultationLog;
import com.music.mapper.ConsultationLogMapper;
import com.music.service.ConsultationLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationLogServiceImpl extends ServiceImpl<ConsultationLogMapper, ConsultationLog> implements ConsultationLogService {

    @Autowired
    ConsultationLogMapper consultationLogMapper;
    @Override
    public IPage<ConsultationLog> pageCC(IPage<ConsultationLog> page, @Param(Constants.WRAPPER)Wrapper wrapper){
        return consultationLogMapper.pageCC(page,wrapper);
    }

    @Override
    public List<ConsultationLog> getByAptId(Integer aptId) {
        return consultationLogMapper.getByAptId(aptId);
    }
}
