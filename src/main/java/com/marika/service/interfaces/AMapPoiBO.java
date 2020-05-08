package com.marika.service.interfaces;

import com.marika.po.AMapPoiRequest;
import com.marika.po.AMapPoiResponse;

import java.util.List;

public interface AMapPoiBO {
    List<AMapPoiResponse> getAMapPoi(AMapPoiRequest ampr);
}
