package com.nj.play.redis.redisson.redisspringdemo.geo.service;

import com.nj.play.redis.redisson.redisspringdemo.geo.dto.GeoLocation;
import com.nj.play.redis.redisson.redisspringdemo.geo.dto.Restaurant;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class RestaurantLocatorService {
    private RGeoReactive<Restaurant>  geo;
    private RMapReactive<String, GeoLocation> map;

    public RestaurantLocatorService(RedissonReactiveClient redissonReactiveClient) {
        this.geo = redissonReactiveClient.getGeo("restaurant:geo", new TypedJsonJacksonCodec(Restaurant.class));
        this.map = redissonReactiveClient.getMap("restaurant:map", new TypedJsonJacksonCodec(String.class, GeoLocation.class));
    }

    public Flux<Restaurant> getRestaurants(final String zipCode) {
        return this.map.get(zipCode)
                .map(geo1 -> GeoSearchArgs.from(geo1.getLongitude(), geo1.getLatitude()).radius(5, GeoUnit.MILES))
                .flatMap(gs -> this.geo.search(gs))
                .flatMapIterable(Function.identity());

    }
}
