package com.letsgo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.letsgo.mapper") // 扫描dao层
public class LetsgoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsgoApplication.class, args);
    }

}
