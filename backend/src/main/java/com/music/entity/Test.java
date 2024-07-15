package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("psychologicaltests")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "test_id", type = IdType.AUTO)
    private Long testId;

    @NotNull(message = "belongingId不为空")
    private Integer belongingId;

    private String testName;

    private String testContent;

    private Integer testScore;

    private String testChoA;

    private String testChoB;

    private String testChoC;

    private String testChoD;

    private Integer testProportionA;

    private Integer testProportionB;

    private Integer testProportionC;

    private Integer testProportionD;

}
