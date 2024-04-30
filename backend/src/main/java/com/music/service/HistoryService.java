package com.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.History;

import java.util.List;

public interface HistoryService extends IService<History> {
    List<History> getHistoryMusic(Long id);
}
