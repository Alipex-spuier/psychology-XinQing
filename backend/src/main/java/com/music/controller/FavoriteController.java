package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.Favorite;
import com.music.service.FavoriteService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @RequiresAuthentication
    @GetMapping("/{id}")
    public Result getFavorite(@PathVariable(name="id")Long id) {
        Map<String,Object> favoriteMap=new HashMap<>();
        List<Favorite> favorite=favoriteService.getFavoriteMusic(id);
        favoriteMap.put("records",favorite);
        return Result.succ(favoriteMap);
    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result addFavorite(@RequestBody Favorite favorite) {
        favorite.setCreated(new Date());
        return Result.succ(favoriteService.saveOrUpdate(favorite));
    }
    @RequiresAuthentication
    @DeleteMapping("/delete/{id}")
    public Result deleteFavorite(@PathVariable Integer id){
        return Result.succ(favoriteService.removeById(id));
    }
}
