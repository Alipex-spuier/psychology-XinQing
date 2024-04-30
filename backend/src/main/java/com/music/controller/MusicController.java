package com.music.controller;

import com.music.common.lang.Result;
import com.music.service.MusicService;
import com.music.vo.MusicRecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/musics")
    public Result allMusic() {
        Map<String,Object> musicMap=new HashMap<>();
        List<MusicRecommendVO> musicRecommendVOS=musicService.getMusicRecommendVOList();
        musicMap.put("records",musicRecommendVOS);
        return Result.succ(musicMap);
    }
}
