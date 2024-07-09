package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("articles")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "art_id", type = IdType.AUTO)
    private Long artId;

    @NotBlank(message = "作者不能为空")
    private String artAuthor;

    @NotBlank(message = "标题不能为空")
    private String artTitle;

    private String artContent;

    @NotBlank(message = "封面图片不能为空")
    private String artPic;

    private Date artTime;

}
