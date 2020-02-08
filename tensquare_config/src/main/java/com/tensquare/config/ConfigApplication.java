package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author caoqian
 * @ClassName ConfigApplication  集中配置组件模块
 * @Date 2020/2/8 12:00
 * @Version 1.0
 */
@EnableConfigServer//开启配置服务
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class);
    }
}
