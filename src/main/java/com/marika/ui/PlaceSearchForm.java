package com.marika.ui;

import com.marika.po.PlaceSearchResponse;

import java.util.List;

public class PlaceSearchForm {
    private String query;
    private String tag;
    private String region;

    private Integer total;
    private List<PlaceSearchResponse> responseList;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PlaceSearchResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<PlaceSearchResponse> responseList) {
        this.responseList = responseList;
    }

    @Override
    public String toString() {
        return "PlaceSearchForm{" +
                "query='" + query + '\'' +
                ", tag='" + tag + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
