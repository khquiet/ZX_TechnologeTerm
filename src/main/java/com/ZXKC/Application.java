package com.ZXKC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 这里的注解必须是 org.springframework.boot.autoconfigure.SpringBootApplication
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(Application.class, args);
        System.out.println("项目启动成功！厨房订单系统已就绪。");
    }
}