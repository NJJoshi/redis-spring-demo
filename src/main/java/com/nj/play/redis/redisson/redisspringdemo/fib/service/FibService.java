package com.nj.play.redis.redisson.redisspringdemo.fib.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FibService {

    //Need to have a cache evict strategy
    @Cacheable(value = "math:fib", key = "#n")
    public int getFib(int n, String name) {
        System.out.println("Calculating fib for " + n + " and name " + name);
        return fib(n);
    }

    // PUT/ POST / POST/ PATCH / DELETE
    @CacheEvict(value = "math:fib", key = "#n")
    public void clearCache(int n) {
        System.out.println("Clearing cache for key =" + n);

    }

    private int fib(int n) {
        if(n < 2) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}
