package com.marika.mapper;

import com.marika.po.AMapAdCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AMapAdCodeOpt {
    List<AMapAdCode> queryAdCode();
    List<AMapAdCode> queryAdCodeWithProvince();
    List<AMapAdCode> queryAdCodeByProvince(@Param("cityPrefix") String cityPrefix);
    int queryAdCodeTotal();
}
