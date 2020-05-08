package com.marika.po;

import lombok.Data;

@Data
public class Coordinate {
    private double lng;
    private double lat;

    public Coordinate(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
