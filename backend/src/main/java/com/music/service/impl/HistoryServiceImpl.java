package com.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.History;
import com.music.mapper.HistoryMapper;
import com.music.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public List<History> getHistoryMusic(Long id) {
        return historyMapper.selectHistory(id);
    }
}
