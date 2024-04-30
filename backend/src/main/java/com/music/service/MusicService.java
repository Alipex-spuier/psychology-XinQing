package com.music.service;

import com.music.entity.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.vo.MusicRecommendVO;

import java.util.List;

public interface MusicService extends IService<Music> {

    List<MusicRecommendVO> getMusicRecommendVOList();
}
