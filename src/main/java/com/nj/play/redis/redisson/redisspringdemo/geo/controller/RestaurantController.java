package com.nj.play.redis.redisson.redisspringdemo.geo.controller;

import com.nj.play.redis.redisson.redisspringdemo.geo.dto.Restaurant;
import com.nj.play.redis.redisson.redisspringdemo.geo.service.RestaurantLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("geo")
public class RestaurantController {

    @Autowired
    private RestaurantLocatorService  restaurantLocatorService;

    @GetMapping("{zip}")
    public Flux<Restaurant> getRestaurants(@PathVariable String zip) {
        return this.restaurantLocatorService.getRestaurants(zip);
    }
}
