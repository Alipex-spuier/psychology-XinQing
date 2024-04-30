package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_recommend")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recommend_id", type = IdType.AUTO)
    private Integer recommendId;

    private Double emotionRecommendScore;

    private Double genderRecommendScore;

    private String gender;

    private String emotion;

}
