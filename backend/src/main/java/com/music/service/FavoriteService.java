package com.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Favorite;

import java.util.List;

public interface FavoriteService extends IService<Favorite> {
    List<Favorite> getFavoriteMusic(Long userId);
}
