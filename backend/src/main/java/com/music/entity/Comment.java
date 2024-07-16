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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("articlescomments")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comments_id", type = IdType.AUTO)
    @NotNull(message = "评论Id不能为空")
    private Long commentsId;

    @NotNull(message = "用户不能为空")
    private Integer userId;

    @NotNull(message = "所属文章不能为空")
    private Integer artId;

    private String commentsContent;

    private Long commentsTime;
}
