package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author caoqian
 * @ClassName SearchApplication
 * @Date 2020/1/25 14:32
 * @Version 1.0
 */
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }

    /**
     * 自行制定id
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
