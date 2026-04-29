package com.nj.play.redis.redisson.redisspringdemo;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class RedisSpringDemoApplicationTests {

    @Autowired
    private ReactiveStringRedisTemplate template;

    @Autowired
    private RedissonReactiveClient client;

    @RepeatedTest(3)
    void springDataRedisTest() {
        ReactiveValueOperations<String, String> valueOperation = this.template.opsForValue();
        long start = System.currentTimeMillis();
        Mono<Void> mono = Flux.range(1, 500_000)
                .flatMap(i -> valueOperation.increment("usr:1:visit", i))
                .then();
        StepVerifier.create(mono).verifyComplete();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");
    }

    @RepeatedTest(3)
    void springDataRedissonTest() {
        RAtomicLongReactive atomicLong = this.client.getAtomicLong("user:2:visit");
        long start = System.currentTimeMillis();
        Mono<Void> mono = Flux.range(1, 500_000)
                .flatMap(i -> atomicLong.incrementAndGet())
                .then();
        StepVerifier.create(mono).verifyComplete();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");
    }

}
