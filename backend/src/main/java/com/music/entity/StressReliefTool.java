package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.shiro.SecurityUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.PrimitiveIterator;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stressrelieftools")
public class StressReliefTool implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "工具名字不能为空")
    private String toolName;

    private String toolDescription;

    private String toolLink;
}
