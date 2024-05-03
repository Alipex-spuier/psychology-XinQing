package com.music.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountProfile implements Serializable {
    private Long userId;

    private String username;

    private String avatar;

    private String email;

    private String work;

    private String country;

    private Integer age;

    private Date created;

    private Date lastLogin;
}
