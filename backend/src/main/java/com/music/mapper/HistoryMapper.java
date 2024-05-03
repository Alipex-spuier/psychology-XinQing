package com.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.History;

import java.util.List;

public interface HistoryMapper extends BaseMapper<History> {
    List<History> selectHistory(Long userId);
}
