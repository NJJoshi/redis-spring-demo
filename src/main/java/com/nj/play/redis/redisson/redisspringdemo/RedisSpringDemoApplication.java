package com.nj.play.redis.redisson.redisspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class RedisSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringDemoApplication.class, args);
    }

}
