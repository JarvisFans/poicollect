package com.marika.ui;

import com.marika.po.AMapPoiResponse;
import lombok.Data;

import java.util.List;

@Data
public class AMapPoiForm {
    private String city;
    private String bigtype;
    private String midtype;
    private String subtype;

    private Integer count;
    private String filename;

    private String types;
    private List<AMapPoiResponse> responseList;
}
