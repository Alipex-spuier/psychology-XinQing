package com.music.service.impl;

import com.music.entity.Music;
import com.music.mapper.MusicMapper;
import com.music.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注官网：同学邦
 * @since 2023-07-21
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

}
