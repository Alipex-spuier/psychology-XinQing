package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_history")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "history_id", type = IdType.AUTO)
    private Long historyId;

    private Long userId;

    private Long musicId;

    private Date created;

}
