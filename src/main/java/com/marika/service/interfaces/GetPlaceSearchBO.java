package com.marika.service.interfaces;

import com.marika.po.AMapPoiResponse;
import com.marika.po.PlaceSearchResponse;
import com.marika.po.RegionRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface GetPlaceSearchBO {
    void getPOIByRegion(RegionRequest request);
    HSSFWorkbook exportExcel(List<PlaceSearchResponse> list)throws Exception;

    HSSFWorkbook exportAMapExcel(List<AMapPoiResponse> list)throws Exception;
}
