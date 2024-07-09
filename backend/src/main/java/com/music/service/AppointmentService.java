package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {

    Appointment searchAllByUserId(Long userId);

    Appointment searchAllByExpertId(Long expertId);

    IPage<Appointment> pageCC(IPage<Appointment> page, Wrapper wrapper);
}
