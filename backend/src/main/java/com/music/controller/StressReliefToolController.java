package com.music.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.StressReliefTool;
import com.music.service.StressReliefToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stressReliefTool")
public class StressReliefToolController {
    @Autowired
    StressReliefToolService stressReliefToolService;
    @GetMapping("/index")
    public Result index(){
        return Result.succ(stressReliefToolService.list());
    }

    @PostMapping("/index/{id}")
    public Result indexById(@PathVariable Integer id){
        return Result.succ(stressReliefToolService.getById(id));
    }

    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam queryPageParam){
        Page<StressReliefTool> page = new Page<>();

        page.setSize(queryPageParam.getPageSize());
        page.setCurrent(queryPageParam.getPageNum());

        IPage<StressReliefTool> result = stressReliefToolService.pageCC(page,null);
        return Result.succ(result.getRecords());
    }

    @PutMapping("/update")
    public Result update(@RequestBody StressReliefTool stressReliefTool){
        stressReliefToolService.updateById(stressReliefTool);
        StressReliefTool newStressReliefTool = stressReliefToolService.getById(stressReliefTool.getId());
        return Result.succ(MapUtil.builder()
                .put("id",newStressReliefTool.getId())
                .put("toolName",newStressReliefTool.getToolName())
                .put("toolDescription",newStressReliefTool.getToolDescription())
                .put("toolLink",newStressReliefTool.getToolLink())
                .map());
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.succ(stressReliefToolService.removeById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody StressReliefTool stressReliefTool){
        return stressReliefToolService.save(stressReliefTool)?Result.succ(stressReliefTool):Result.fail("保存失败！");
    }
}

