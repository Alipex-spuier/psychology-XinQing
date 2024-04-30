package com.music.service.impl;

import com.music.entity.Music;
import com.music.mapper.MusicMapper;
import com.music.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

}
