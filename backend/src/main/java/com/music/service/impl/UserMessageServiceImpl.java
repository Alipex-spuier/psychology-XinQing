package com.music.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.mapper.UserMessageMapper;
import com.music.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.music.entity.UserMessage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {
    @Autowired
    UserMessageMapper userMessageMapper;
    @Override
    public IPage pageCC(IPage<UserMessage> page, Wrapper wrapper) {
        return userMessageMapper.pageCC(page,wrapper);
    }
}
