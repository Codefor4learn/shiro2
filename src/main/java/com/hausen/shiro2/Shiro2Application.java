package com.hausen.shiro2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hausen.shiro2.dao")
public class Shiro2Application {

    public static void main(String[] args) {
        SpringApplication.run(Shiro2Application.class, args);
    }

}
