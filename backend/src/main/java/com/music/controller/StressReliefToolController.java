package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.StressReliefTool;
import com.music.service.StressReliefToolService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stressReliefTool")
public class StressReliefToolController {

    private final StressReliefToolService stressReliefToolService;

    @Autowired
    public StressReliefToolController(StressReliefToolService stressReliefToolService){
        this.stressReliefToolService = stressReliefToolService;
    }
    @ApiOperation(value = "用于全查询（无分页） "+
            "不用传参数")
    @GetMapping("/index")
    public Result index(){
        return Result.succ(stressReliefToolService.list());
    }

    @ApiOperation(value = "用于单独查询某一条记录 "+
            " \"id\":* ")
    @PostMapping("/index/{id}")
    public Result indexById(@PathVariable Integer id){
        return ObjectUtil.isNotEmpty(stressReliefToolService.getById(id))?Result.succ(stressReliefToolService.getById(id)):Result.fail("没有这个解压工具");
    }

    @ApiOperation(value = "用于分页查询"+
            " \"pageSize\":1,\n" +
            "    \"pageNum\":1")
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam queryPageParam){
        Page<StressReliefTool> page = new Page<>();

        page.setSize(queryPageParam.getPageSize());
        page.setCurrent(queryPageParam.getPageNum());

        IPage<StressReliefTool> result = stressReliefToolService.pageCC(page,null);
        return Result.succ(result.getRecords());
    }
    @ApiOperation(value = "用于根据id更新一条记录 id和toolName是必须的"+
            "    \"id\":1,\n" +
            "    \"toolName\":\"test1\",\n" +
            "    \"toolDescription\":\"test11\",\n" +
            "    \"toolLink\":\"url\"")
    @PutMapping("/update")
    public Result update(@RequestBody StressReliefTool stressReliefTool){
        if(ObjectUtil.isEmpty(stressReliefToolService.getById(stressReliefTool.getId())))
            return Result.fail("没有这个解压工具");
        if(ObjectUtil.isNotEmpty(stressReliefTool.getToolDescription())&&stressReliefTool.getToolDescription().trim().isEmpty())
            return Result.fail("修改后的解压工具描述不能为空格");
        if(stressReliefTool.getToolName()!=null&&StringUtils.isEmpty(stressReliefTool.getToolName()))
            return Result.fail("解压工具名字不能为空");

        stressReliefToolService.updateById(stressReliefTool);
        StressReliefTool newStressReliefTool = stressReliefToolService.getById(stressReliefTool.getId());
        return Result.succ(MapUtil.builder()
                .put("id",newStressReliefTool.getId())
                .put("toolName",newStressReliefTool.getToolName())
                .put("toolDescription",newStressReliefTool.getToolDescription())
                .put("toolLink",newStressReliefTool.getToolLink())
                .map());
    }

    @ApiOperation(value = "用于删除一条记录"+
            "\"id\": *")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return ObjectUtil.isNotEmpty(stressReliefToolService.getById(id))?Result.succ(stressReliefToolService.removeById(id)):Result.fail("没有这个解压工具");
    }

    @ApiOperation(value = "用于添加一条stressReliefTool"+
            "\"toolName\":\"test1\",\n" +
            "    \"toolDescription\":\"test11\",\n" +
            "    \"toolLink\":\"url\"")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody StressReliefTool stressReliefTool){
        if(!stressReliefToolService.save(stressReliefTool))
            return Result.fail("保存失败");
        return Result.succ(stressReliefToolService.getById(stressReliefTool.getId()));
    }
}

