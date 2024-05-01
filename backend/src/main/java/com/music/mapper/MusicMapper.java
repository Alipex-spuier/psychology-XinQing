package com.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Music;
import com.music.vo.MusicRecommendVO;

import java.util.List;

public interface MusicMapper extends BaseMapper<Music> {
    List<MusicRecommendVO> selectMusicRecommendVOList();
    List<Music> selectMusicList(String pattern);
}