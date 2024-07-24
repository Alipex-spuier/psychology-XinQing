package com.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.music.mapper")
@SpringBootApplication
public class MusicBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicBackendApplication.class, args);
    }

}
