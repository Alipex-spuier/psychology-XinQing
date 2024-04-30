package com.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.Favorite;
import com.music.mapper.FavoriteMapper;
import com.music.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Override
    public List<Favorite> getFavoriteMusic(Long id) {
        return favoriteMapper.selectFavorite(id);
    }
}
