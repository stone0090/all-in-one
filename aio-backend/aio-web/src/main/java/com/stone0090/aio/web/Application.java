package com.stone0090.aio.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stone0090.aio")
@MapperScan("com.stone0090.aio.dao.mybatis")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}