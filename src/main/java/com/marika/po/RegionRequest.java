package com.marika.po;

/**
 * 根据行政区划进行区域检索
 */
public class RegionRequest {
    private String query; //not null
    private String tag;
    private String region; //not null
    private String output; //json或xml
    private String scope; //1基本；2详情
    private int page_size; //单次返回数量，默认10，最大20
    private int page_num; //分页页码
    private String ak; //开发者秘钥

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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    @Override
    public String toString() {
        return "RegionRequest{" +
                "query='" + query + '\'' +
                ", tag='" + tag + '\'' +
                ", region='" + region + '\'' +
                ", output='" + output + '\'' +
                ", scope='" + scope + '\'' +
                ", page_size=" + page_size +
                ", page_num=" + page_num +
                ", ak='" + ak + '\'' +
                '}';
    }
}
