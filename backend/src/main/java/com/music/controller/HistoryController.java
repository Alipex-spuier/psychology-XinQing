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
    @GetMapping("/{id}")
    public Result getHistory(@PathVariable(name = "id") Long id) {
        Map<String, Object> historyMap = new HashMap<>();
        List<History> histories = historyService.getHistoryMusic(id);
        historyMap.put("records", histories);
        return Result.succ(historyMap);
    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result addHistory(@RequestBody History history) {
        history.setCreated(new Date());
        return Result.succ(historyService.saveOrUpdate(history));
    }

}
