package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@TableName("usermessages")
@Data
public class UserMessage implements Serializable {
    @TableId(value = "mes_id", type = IdType.AUTO)
    private Long mesId;

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "adminId不能为空")
    private Long adminId;

    private String mesContent;

    private Long mesTime;
}
