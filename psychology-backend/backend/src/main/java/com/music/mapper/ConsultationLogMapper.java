package com.music.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.music.entity.ConsultationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsultationLogMapper extends BaseMapper<ConsultationLog> {
    IPage<ConsultationLog> pageCC(IPage<ConsultationLog> page ,@Param(Constants.WRAPPER)Wrapper wrapper);

    List<ConsultationLog> getByAptId(Integer aptId);
}
