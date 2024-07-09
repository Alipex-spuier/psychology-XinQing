package com.music.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.User;
import com.music.entity.UserMessage;

public interface UserMessageService extends IService<UserMessage> {
    IPage pageCC(IPage<UserMessage> page, Wrapper wrapper);
}
