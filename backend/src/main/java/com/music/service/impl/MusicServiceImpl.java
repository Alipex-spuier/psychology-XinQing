package com.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Blog;
import com.music.entity.Music;
import com.music.mapper.BlogMapper;
import com.music.mapper.MusicMapper;
import com.music.service.MusicService;
import com.music.vo.MusicRecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>  implements MusicService {
    @Autowired
    private MusicMapper musicMapper;
    @Override
    public List<MusicRecommendVO> getMusicRecommendVOList(){
        List<MusicRecommendVO> musicRecommendVOs=musicMapper.selectMusicRecommendVOList();
        return musicRecommendVOs;
    }
}
