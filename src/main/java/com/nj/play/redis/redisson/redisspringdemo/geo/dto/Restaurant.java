package com.nj.play.redis.redisson.redisspringdemo.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private String id;
    private String city;
    private String zip;
    private String name;
    private double longitude;
    private double latitude;
}
