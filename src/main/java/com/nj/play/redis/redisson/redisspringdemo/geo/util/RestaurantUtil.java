package com.nj.play.redis.redisson.redisspringdemo.geo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nj.play.redis.redisson.redisspringdemo.geo.dto.Restaurant;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class RestaurantUtil {
    public static List<Restaurant> getRestaurants(){
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream =
                RestaurantUtil.class.getClassLoader().getResourceAsStream("restaurant.json");
        try {
              return mapper.readValue(stream, new TypeReference<List<Restaurant>>() {

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
         return Collections.emptyList();
    }
}
