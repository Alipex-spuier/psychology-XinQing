package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_details")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "details_id", type = IdType.AUTO)
    private Integer detailsId;

    private Integer recommendId;

    private String url;

    private String albumCover;

    private String author;

    private String title;

    private String hotComment;

    private String commentAuthor;

}
