package com.marika.service.impl;

import com.marika.po.AMapPoiResponse;
import com.marika.po.PlaceSearchResponse;
import com.marika.po.RegionRequest;
import com.marika.service.interfaces.GetPlaceSearchBO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class GetPlaceSearchBOImpl implements GetPlaceSearchBO {
    public void getPOIByRegion(RegionRequest request) {

    }

    @Override
    public HSSFWorkbook exportExcel(List<PlaceSearchResponse> list) throws Exception {
        HSSFWorkbook xls;
        int rownum = 0;
        int id = 0;
        xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet("POI检索");
        sheet.setDefaultColumnWidth(15);
        sheet.setColumnWidth(4,30*256);
        sheet.setColumnWidth(5,37*256);
        HSSFRow row=sheet.createRow(rownum++);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("所属省份");
        row.createCell(2).setCellValue("所属城市");
        row.createCell(3).setCellValue("所属区县");
        row.createCell(4).setCellValue("POI名称");
        row.createCell(5).setCellValue("地址");
        row.createCell(6).setCellValue("经度");
        row.createCell(7).setCellValue("纬度");
        for(PlaceSearchResponse region:list){
            row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(id++);
            row.createCell(1).setCellValue(region.getProvince());
            row.createCell(2).setCellValue(region.getCity());
            row.createCell(3).setCellValue(region.getArea());
            row.createCell(4).setCellValue(region.getName());
            row.createCell(5).setCellValue(region.getAddress());
            row.createCell(6).setCellValue(region.getLat());
            row.createCell(7).setCellValue(region.getLng());
        }
        return xls;
    }

    @Override
    public HSSFWorkbook exportAMapExcel(List<AMapPoiResponse> list) throws Exception {
        HSSFWorkbook xls;
        int rownum = 0;
        int id = 1;
        xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet("POI检索");
        sheet.setDefaultColumnWidth(15);
        sheet.setColumnWidth(4,30*256);
        sheet.setColumnWidth(5,37*256);
        sheet.setColumnWidth(6,37*256);
        HSSFRow row=sheet.createRow(rownum++);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("所属省份");
        row.createCell(2).setCellValue("所属城市");
        row.createCell(3).setCellValue("所属区县");
        row.createCell(4).setCellValue("名称");
        row.createCell(5).setCellValue("分类");
        row.createCell(6).setCellValue("地址");
        row.createCell(7).setCellValue("经度");
        row.createCell(8).setCellValue("纬度");
        for(AMapPoiResponse region:list){
            row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(id++);
            row.createCell(1).setCellValue(region.getPname());
            row.createCell(2).setCellValue(region.getCityname());
            row.createCell(3).setCellValue(region.getAdname());
            row.createCell(4).setCellValue(region.getName());
            row.createCell(5).setCellValue(region.getType());
            row.createCell(6).setCellValue(region.getAddress());
            row.createCell(7).setCellValue(region.getLng());
            row.createCell(8).setCellValue(region.getLat());
        }
        return xls;
    }
}
