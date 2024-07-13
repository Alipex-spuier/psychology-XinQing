package com.music.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.music.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {

    Appointment searchAllByUserId(Long userId);

    Appointment searchAllByExpertId(Long expertId);

    Appointment getOneByExpertIdAndAptTime(@Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Appointment> pageCC(IPage<Appointment> page,@Param(Constants.WRAPPER) Wrapper wrapper);

    Integer accountByDay(@Param(Constants.WRAPPER) Wrapper wrapper);
}
