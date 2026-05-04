package com.nj.play.redis.redisson.redisspringdemo.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GeoLocation {
    private double longitude;
    private double latitude;
}
