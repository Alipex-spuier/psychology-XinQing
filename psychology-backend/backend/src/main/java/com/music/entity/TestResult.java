package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("usertestresults")
public class TestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "res_id", type = IdType.AUTO)
    //@NotNull(message = "测试Id不能为空")
    private Long resId;

    @NotNull(message = "用户不能为空")
    private Integer userId;

    private String resResult;

    @NotNull(message = "所属题单不能为空")
    private Integer belongingId;

    private Date resTime;
}
