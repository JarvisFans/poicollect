package com.marika.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AMapPoiRequest {
    private String key;
    private String types;
    private String city;
    private String citylimit;
    private String offset;
    private String page;
    private String output;
}
