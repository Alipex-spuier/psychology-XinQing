package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Expert;
import com.music.entity.User;

import java.util.List;

public interface ExpertService extends IService<Expert> {
    public String registerService(Expert expert);
    IPage pageCC(IPage<Expert> page, Wrapper wrapper);

    List<Expert> searchAllByExDire(String exDire);

    Expert searchByExName(String exName);
}
