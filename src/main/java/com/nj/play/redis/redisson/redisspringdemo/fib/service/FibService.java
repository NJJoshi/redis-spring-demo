package com.nj.play.redis.redisson.redisspringdemo.fib.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FibService {

    @Cacheable(value = "math:fib", key = "#n")
    public int getFib(int n, String name) {
        System.out.println("Calculating fib for " + n + " and name " + name);
        return fib(n);
    }

    private int fib(int n) {
        if(n < 2) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}
