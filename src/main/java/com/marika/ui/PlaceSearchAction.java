package com.marika.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marika.po.PlaceSearchResponse;
import com.marika.service.impl.GetPlaceSearchBOImpl;
import com.marika.service.interfaces.GetPlaceSearchBO;
import com.marika.util.HttpRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceSearchAction extends ActionSupport implements ModelDriven<PlaceSearchForm> {
    private PlaceSearchForm psf = new PlaceSearchForm();
    private GetPlaceSearchBO getPlaceSearchBO = new GetPlaceSearchBOImpl();
    private String contentType;
    private String fileName;
    private InputStream inputStream;
    // prod set
    public static final String FILE_PATH = "/data/poidata/";
    // local set
    // public static final String FILE_PATH = "/Users/fan/IdeaProjects/poicollect/poidata/";

    public String placeSearchInit() throws Exception {
        String url = "http://api.map.baidu.com/place/v2/search";
        String params = "";
        int pagenum = 0;
        if (psf.getQuery() == null || "".equals(psf.getQuery())) {
            return ERROR;
        } else {
            params += "query=" + psf.getQuery() + "&";
        }
        if (psf.getTag() != null && !"".equals(psf.getTag())) {
            params += "tag=" + psf.getTag() + "&";
        }
        if (psf.getRegion() == null || "".equals(psf.getRegion())) {
            return ERROR;
        } else {
            params += "region=" + psf.getRegion() + "&";
        }
        params += "output=json&ak=1nZ8duKc8Uy8yETDPN3Bmi4zlCXqFYFy&page_num=";
        List<PlaceSearchResponse> regionList = new ArrayList<>();
        // 第一次查询，获取total
        Map maps = (Map) JSON.parse(HttpRequest.sendGet(url, params + pagenum++));
        int total = Integer.parseInt(maps.get("total").toString());
        psf.setTotal(total);
        if (total == 0) {
            return ERROR;
        } else {
            String results = maps.get("results").toString();
            List<PlaceSearchResponse> tmpList = JSONObject.parseArray(results, PlaceSearchResponse.class);
            regionList.addAll(tmpList);
            total += -10;
            // 根据total决定是否loop
            while (total > 0) {
                maps = (Map) JSON.parse(HttpRequest.sendGet(url, params + pagenum++));
                results = maps.get("results").toString();
                tmpList = JSONObject.parseArray(results, PlaceSearchResponse.class);
                regionList.addAll(tmpList);
                total += -10;
            }
        }
        // 拆分经纬度
        String[] location;
        for (PlaceSearchResponse region : regionList) {
            location = region.getLocation().split("[:,}]");
            region.setLng(location[1]);
            region.setLat(location[3]);
        }
        // 文件存储
        HSSFWorkbook workbook = getPlaceSearchBO.exportExcel(regionList);
        fileName = psf.getRegion() + "-" + psf.getQuery() + ("".equals(psf.getTag()) ? "" : "-" + psf.getTag()) + ".xls";
        File file = new File(FILE_PATH + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.flush();
        fos.close();

        psf.setResponseList(regionList);
        return SUCCESS;
    }

    public String placeSearchDownload() {
        contentType = "application/vnd.ms-excel";
        fileName = psf.getRegion() + "-" + psf.getQuery() + ("".equals(psf.getTag()) ? "" : "-" + psf.getTag()) + ".xls";
        try {
            inputStream = new FileInputStream(FILE_PATH + fileName);
            fileName = new String(fileName.getBytes(),"ISO8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @Override
    public PlaceSearchForm getModel() {
        if (psf == null)
            psf = new PlaceSearchForm();
        return psf;
    }

    public PlaceSearchForm getPsf() {
        return psf;
    }

    public void setPsf(PlaceSearchForm psf) {
        this.psf = psf;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
