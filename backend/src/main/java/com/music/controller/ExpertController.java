package com.music.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.common.lang.Result;
import com.music.common.page.QueryPageParam;
import com.music.entity.Expert;
import com.music.entity.User;
import com.music.service.ExpertService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/expert")
public class ExpertController {

    private final ExpertService expertService;

    @Autowired
    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中所有expert "+
            "{不传参}"
    )
    @GetMapping("/index")
    public Result index() {
        return Result.succ(expertService.list());
    }

    @RequiresAuthentication
    @ApiOperation(value ="列出数据库中exId为{exId}的专家 "+
            "{参数通过url传入}"
    )
    @PostMapping("/index/{exId}")
    public Result index(@PathVariable Integer exId) {
        Expert expert = expertService.getById(exId);
        if(ObjectUtil.isEmpty(expert))
            return Result.fail("没有这个专家");
        return Result.succ(expert);
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过名字（模糊查询）分页查询数据库中的专家 "+
            "{\"pageSize\":2,\n" +
            "\"pageNum\":1,\n" +
            "\"param\":{\n" +
            "    \"name\":\"te\"\n" +
            "}}"
    )
    @PostMapping("/indexPage")
    public Result indexPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        if(name==null || ObjectUtil.isEmpty(name.replace(" ",""))){
            return Result.fail("name不存在");
        }

        Page<Expert> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Expert> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Expert::getExName,name);

        IPage result = expertService.pageCC(page,lambdaQueryWrapper);
        return Result.succ(result.getRecords());
    }

    @RequiresAuthentication
    @ApiOperation(value ="更新专家信息,exId必填，其他几个想改啥填啥 "+
            "{\"exId\":5,\n" +
            "\"exName\": \"test5,更新测试\",\n" +
            "\"exPassword\": \"123\",\n" +
            "\"exEmail\":\"123@qq.com\",\n" +
            "\"avatar\": \"头像\",\n" +
            "\"exBio\":\"exBio测试\",\n" +
            "\"exDire\":\"exDire测试\",\n" +
            "\"checked\":1(0表示未通过验证，1表示通过验证),\n" +
            "\"exQualification\":\"exQualification测试\"\n" +
            "}"
    )
    @PutMapping("/update")
    public Result update(@RequestBody Expert expert){
        if(expert.getExId() == null || ObjectUtil.isEmpty(expertService.getById(expert.getExId()))){
            return Result.fail("找不到expert");
        }
        if(expertService.getById(expert.getExId())==null){
            return Result.fail("该专家不存在！");
        }
        if(expert.getExName()!=null){
            if(ObjectUtil.isEmpty(expert.getExName().replace(" ",""))){
                return Result.fail("exName不能为空");
            }else {
                if(ObjectUtil.isNotEmpty(expertService.searchByExName(expert.getExName()))
                        &&!expert.getExId().equals(expertService.searchByExName(expert.getExName()).getExId()))
                    return Result.fail("昵称已存在");
            }
        }
        if(ObjectUtil.isNotEmpty(expert.getExPassword())) {
            String password = SecureUtil.md5(expert.getExPassword());
            expert.setExPassword(password);
        }
        if(expert.getExEmail()!=null && ObjectUtil.isEmpty(expert.getExEmail().replace(" ",""))){
            return Result.fail("exEmail不能为空");
        }
        expertService.updateById(expert);
        Expert newExpert = expertService.getById(expert.getExId());
        return Result.succ(MapUtil.builder()
                .put("exId", newExpert.getExId())
                .put("exName", newExpert.getExName())
                .put("exEmail", newExpert.getExEmail())
                .put("avatar", newExpert.getAvatar())
                .put("exBio", newExpert.getExBio())
                .put("exDire", newExpert.getExDire())
                .put("exQualification", newExpert.getExQualification())
                .put("checked", newExpert.getChecked())
                .put("createdTime", newExpert.getCreatedTime())
                .map());
    }

    @RequiresAuthentication
    @PostMapping("/save")
    @ApiOperation(value ="新建并保存一个用户，以下两个必填，其他选填 "+
            "{\"exName\":\"test6\",\n" +
            "\"exPassword\":\"123\"}"
    )
    public Result save(@RequestBody @Valid Expert expert){
        String password = SecureUtil.md5(expert.getExPassword());
        expert.setExPassword(password);
        Date date = new Date();
        expert.setCreatedTime(date);
        return expertService.save(expert)?Result.succ(expert):Result.fail("保存失败！");
    }

    @RequiresAuthentication
    @ApiOperation(value ="通过exId删除一个专家 "+
            "{参数通过url传入}"
    )
    @DeleteMapping("/delete/{exId}")
    public Result delete(@PathVariable Integer exId) {
        if(ObjectUtil.isEmpty(expertService.getById(exId))){
            return Result.fail("该条测试已不存在");
        }try{
            expertService.removeById(exId);
        }catch (Exception e){
            return Result.fail("对不起，有外键，请别删！");
        }
        return Result.succ("删除成功！");
    }
}
