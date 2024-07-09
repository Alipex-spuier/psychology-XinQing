package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Appointment;
import com.music.mapper.AppointmentMapper;
import com.music.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
    @Autowired
    AppointmentMapper appointmentMapper;

    @Override
    public Appointment searchAllByUserId(Long userId){
        return appointmentMapper.searchAllByUserId(userId);
    }

    @Override
    public Appointment searchAllByExpertId(Long expertId){
        return appointmentMapper.searchAllByExpertId(expertId);
    }
    @Override
    public IPage<Appointment> pageCC(IPage<Appointment> page, Wrapper wrapper){
        return  appointmentMapper.pageCC(page,wrapper);
    }
}
