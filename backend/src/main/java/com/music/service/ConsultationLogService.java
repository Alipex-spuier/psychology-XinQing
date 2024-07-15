package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.ConsultationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultationLogService extends IService<ConsultationLog> {
    IPage<ConsultationLog> pageCC(IPage<ConsultationLog> page, @Param(Constants.WRAPPER) Wrapper wapper);

    List<ConsultationLog> getByAptId(Integer aptId);
}
