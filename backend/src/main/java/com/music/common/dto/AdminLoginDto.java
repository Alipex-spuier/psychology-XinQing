package com.music.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AdminLoginDto  implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String adminName;
    @NotBlank(message = "密码不能为空")
    private String adminPassword;
}
