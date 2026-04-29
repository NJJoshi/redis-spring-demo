package com.nj.play.redis.redisson.redisspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringDemoApplication.class, args);
    }

}
