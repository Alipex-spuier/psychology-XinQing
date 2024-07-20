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
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("experts")
public class Expert implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ex_id", type = IdType.AUTO)
    private Long exId;

    @NotBlank(message = "昵称不能为空")
    private String exName;

    @NotBlank(message = "密码不能为空")
    private String exPassword;

    @Email(message = "邮箱格式不正确")
    private String exEmail;

    private String avatar;

    private String exBio;

    private String exDire;

    private String exQualification;

    private Integer checked;

    private Date createdTime;
}
