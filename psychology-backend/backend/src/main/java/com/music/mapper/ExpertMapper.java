package com.music.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.music.entity.Expert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExpertMapper extends BaseMapper<Expert> {
    Expert searchByExName(String exName);
    IPage<Expert> pageCC(IPage<Expert> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List<Expert> searchAllByExDire(String exDire);
}
