package com.marika.util;

import com.marika.po.Coordinate;

import java.util.Arrays;

public class CoordTransform {
    private static final double PI = 3.1415926535897932384626;
    private static final double a = 6378245.0;  // 长半轴
    private static final double ee = 0.00669342162296594323; //扁率

    public static Coordinate GCJ02ToWGS84(Coordinate coord){
        double lng = coord.getLng();
        double lat = coord.getLat();
        double dlng = transformLng(lng - 105.0, lat - 35.0);
        double dlat = transformLat(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * PI;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new Coordinate(lng*2 -mglng,lat*2 -mglat);

    }

    private static double transformLat(double lng,double lat){
        double transLng = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        transLng += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        transLng += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        transLng += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return transLng;
    }

    private static double transformLng(double lng,double lat){
        double transLat = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        transLat += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        transLat += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        transLat += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return transLat;
    }

}
