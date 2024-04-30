package com.music.controller;

import com.music.common.lang.Result;
import com.music.entity.History;
import com.music.service.HistoryService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @RequiresAuthentication
    @GetMapping("/history/{id}")
    public Result getHistory(@PathVariable(name="id")Long id) {
        Map<String,Object> historyMap=new HashMap<>();
        List<History> histories=historyService.getHistoryMusic(id);
        historyMap.put("records",histories);
        return Result.succ(historyMap);
    }

}
