package com.music.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ExpertLoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String exName;
    @NotBlank(message = "密码不能为空")
    private String exPassword;
}
