package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan将所有mapper自动装配到spring
@SpringBootApplication
@MapperScan("com.example.mapper")
public class FamilyServicePlatform2Application {

    public static void main(String[] args) {
        SpringApplication.run(FamilyServicePlatform2Application.class, args);
    }

}
