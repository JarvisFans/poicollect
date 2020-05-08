package com.marika.po;


public class PlaceSearchResponse{
    private String area;
    private String uid;
    private String address;
    private String province;
    private String city;
    private String name;
    private String location;
    private String detail;
    private String street_id;

    private String lng; //经度
    private String lat; //纬度

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStreet_id() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "PlaceSearchResponse{" +
                "area='" + area + '\'' +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", detail='" + detail + '\'' +
                ", street_id='" + street_id + '\'' +
                '}';
    }
}
