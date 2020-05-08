package com.marika.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marika.dao.AMapPoiCodeDAO;
import com.marika.po.AMapPoiCode;
import com.marika.po.AMapPoiRequest;
import com.marika.po.AMapPoiResponse;
import com.marika.po.Coordinate;
import com.marika.service.interfaces.AMapPoiBO;
import com.marika.util.CoordTransform;
import com.marika.util.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class AMapPoiBOImpl implements AMapPoiBO {
    AMapPoiCodeDAO aMapPoiCodeDAO = new AMapPoiCodeDAO();
    private static final String KEY = "cbf9bba8face9c372f2158e37aa8a131";
    private static final String URL = "https://restapi.amap.com/v3/place/text";

    @Override
    public List<AMapPoiResponse> getAMapPoi(AMapPoiRequest ampr) {
        int pagenum;
        Coordinate coord;
        ampr.setKey(KEY);
        ampr.setOutput("JSON");
        ampr.setOffset("25");
        ampr.setCitylimit("true");
        String poiType = ampr.getTypes();
        List<AMapPoiCode> typeList = null;
        // List<AMapPoiCode> tmpTypeList = new ArrayList<>();
        try {
            if (poiType.endsWith("0000")) {
                typeList = aMapPoiCodeDAO.queryPoiTypeList(poiType.substring(0, 2));
            } else if (poiType.endsWith("00")) {
                typeList = aMapPoiCodeDAO.queryPoiTypeList(poiType.substring(0, 4));
            } else {
                typeList = aMapPoiCodeDAO.queryPoiTypeList(poiType.substring(0, 6));
            }

        } catch (Exception e) {
            log.error("", e);
        }

        if (typeList == null) {
            System.out.println("未获取list");
            return null;
        } else {
            for (AMapPoiCode poi : typeList)
                System.out.println(poi.getTypecode());
        }

        List<AMapPoiResponse> regionList = new ArrayList<>();
        int count;
        String params;

        for (AMapPoiCode poi : typeList) {
            pagenum = 1;
            ampr.setTypes(poi.getTypecode());
            ampr.setPage(String.valueOf(pagenum++));
            params = this.appendParams(ampr);
            while (true) {
                System.out.println(params);
                Map maps = (Map) JSON.parse(HttpRequest.sendGet(URL, params));
                params = params.substring(0, params.lastIndexOf("=") + 1) + pagenum++;
                // 判断查询结果
                if ("1".equals(maps.get("status")) && "OK".equals(maps.get("info"))) {
                    count = Integer.parseInt(maps.get("count").toString());
                    if (count == 0) {
                        break;
                    } else {
                        List<AMapPoiResponse> tmpList = JSONObject.parseArray(maps.get("pois").toString(), AMapPoiResponse.class);
                        regionList.addAll(tmpList);
                    }
                } else {
                    log.info("请求中断");
                    return null;
                }
            }
        }
        // 去重
        Set<AMapPoiResponse> tmpSet = new HashSet<>(regionList);
        regionList.clear();
        regionList.addAll(tmpSet);

        // 拆分经纬度
        String[] location;
        for (AMapPoiResponse region : regionList) {
            location = region.getLocation().split(",");
            coord = new Coordinate(Double.parseDouble(location[0]),Double.parseDouble(location[1]));
            // 坐标纠偏
            coord = CoordTransform.GCJ02ToWGS84(coord);
            region.setLng(String.valueOf(coord.getLng()));
            region.setLat(String.valueOf(coord.getLat()));
        }
        return regionList;
    }

    private String appendParams(AMapPoiRequest ampr) {
        String params = "key=" + ampr.getKey() + "&city=" + ampr.getCity() +
                "&types=" + ampr.getTypes() + "&citylimit=" + ampr.getCitylimit() +
                "&output=" + ampr.getOutput() + "&offset=" + ampr.getOffset() +
                "&page=" + ampr.getPage();
        return params;
    }
}
