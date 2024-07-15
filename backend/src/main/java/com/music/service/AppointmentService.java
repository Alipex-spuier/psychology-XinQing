package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Appointment;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.List;

public interface AppointmentService extends IService<Appointment> {

    Appointment searchAllByUserId(Long userId);

    List<Appointment> searchAllByExpertId(Long expertId);

    IPage<Appointment> pageCC(IPage<Appointment> page, Wrapper wrapper);

    Integer accountByDay(Wrapper wrapper);

    Appointment getOneByExpertIdAndAptTime(Wrapper wrapper);

}
