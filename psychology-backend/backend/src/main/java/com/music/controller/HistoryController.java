package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.History;
import com.music.service.HistoryService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @RequiresAuthentication
    @GetMapping("/{userId}")
    public Result getHistory(@PathVariable(name = "userId") Long userId) {
        Map<String, Object> historyMap = new HashMap<>();
        List<History> histories = historyService.getHistoryMusic(userId);
        historyMap.put("records", histories);
        return Result.succ(historyMap);
    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result addHistory(@RequestBody History history) {
        history.setCreated(new Date());
        return Result.succ(historyService.saveOrUpdate(history));
    }
    @RequiresAuthentication
    @DeleteMapping("/delete")
    public Result deleteHistory(@RequestBody History history) {
        Map<String, Object> hisMap = new HashMap<>();
        hisMap.put("user_id", history.getUserId());
        hisMap.put("music_id", history.getMusicId());
        return Result.succ(historyService.removeByMap(hisMap));
    }
}
