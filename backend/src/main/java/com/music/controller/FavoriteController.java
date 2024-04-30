package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.Favorite;
import com.music.service.FavoriteService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @RequiresAuthentication
    @GetMapping("/favorite/{id}")
    public Result getFavorite(@PathVariable(name="id")Long id) {
        Map<String,Object> favoriteMap=new HashMap<>();
        List<Favorite> favorite=favoriteService.getFavoriteMusic(id);
        favoriteMap.put("records",favorite);
        return Result.succ(favoriteMap);
    }

}
