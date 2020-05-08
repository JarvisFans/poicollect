package com.marika.mapper;

import com.marika.po.AMapPoiCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AMapPoiCodeOpt {
    List<AMapPoiCode> getPoiTypeListByPrefix(@Param("prefix") String prefix);
}
