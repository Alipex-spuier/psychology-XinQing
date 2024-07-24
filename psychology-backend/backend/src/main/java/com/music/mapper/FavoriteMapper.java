package com.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Favorite;

import java.util.List;

public interface FavoriteMapper extends BaseMapper<Favorite> {
    List<Favorite> selectFavorite(Long userId);
}
