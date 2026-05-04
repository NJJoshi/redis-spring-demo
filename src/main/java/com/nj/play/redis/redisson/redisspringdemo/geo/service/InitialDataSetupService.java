package com.nj.play.redis.redisson.redisspringdemo.geo.service;

import com.nj.play.redis.redisson.redisspringdemo.geo.dto.GeoLocation;
import com.nj.play.redis.redisson.redisspringdemo.geo.dto.Restaurant;
import com.nj.play.redis.redisson.redisspringdemo.geo.util.RestaurantUtil;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InitialDataSetupService implements CommandLineRunner {

    private RGeoReactive<Restaurant> restaurantGeo;
    private RMapReactive<String, GeoLocation> restaurantMap;

    @Autowired
    private RedissonReactiveClient redissonReactiveClient;

    @Override
    public void run(String... args) throws Exception {
        this.restaurantGeo = this.redissonReactiveClient.getGeo("restaurant:geo", new TypedJsonJacksonCodec(Restaurant.class));
        this.restaurantMap = this.redissonReactiveClient.getMap("restaurant:map", new TypedJsonJacksonCodec(String.class, GeoLocation.class));
        Flux.fromIterable(RestaurantUtil.getRestaurants())
                .flatMap(r -> this.restaurantGeo.add(r.getLongitude(), r.getLatitude(), r).thenReturn(r))
                .flatMap(restaurant -> this.restaurantMap.fastPut(restaurant.getZip(),  GeoLocation.of(restaurant.getLongitude(), restaurant.getLatitude())))
                .doFinally(s -> System.out.println("restaurants added " + s))
                .subscribe();
    }
}
