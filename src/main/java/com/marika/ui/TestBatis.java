package com.marika.ui;

import com.marika.dao.AMapPoiCodeDAO;
import com.marika.po.AMapPoiCode;

import java.util.List;

public class TestBatis {
    public static void main(String[] args) {
        try {
            List<AMapPoiCode> list = new AMapPoiCodeDAO().queryPoiTypeList("09");
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
