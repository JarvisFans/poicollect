package com.marika.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AMapPoiResponse {
    // 判断请求成功 使用status=1,info=OK
    // private String status;
    // private String info;
    // 根据count确定请求发送的次数
    // private String count;
    // 基本信息
    private String name;
    private String type;
    // 区域信息
    private String pname;
    private String cityname;
    private String adname;
    // 地理信息
    private String address;
    private String location;
    private String lng;
    private String lat;
}
