package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("appointments")
public class Appointment implements Serializable {
    @TableId(value = "apt_id", type = IdType.AUTO)
    private Long aptId;

    @NotNull(message = "预约的用户id不能为空")
    @Valid
    private Long userId;

    @NotNull(message = "预约的专家id不能为空")
    @Valid
    private Long expertId;

    @NotNull(message = "预约时间不能为空")
    @Valid
    private Date aptTime;

    private String aptStatus;
}
