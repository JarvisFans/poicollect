package com.marika.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marika.dao.AMapAdCodeDAO;
import com.marika.po.AMapPoiRequest;
import com.marika.po.AMapPoiResponse;
import com.marika.service.impl.AMapPoiBOImpl;
import com.marika.service.impl.GetPlaceSearchBOImpl;
import com.marika.service.interfaces.AMapPoiBO;
import com.marika.service.interfaces.GetPlaceSearchBO;
import com.marika.util.HttpRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class AMapPoiAction extends ActionSupport implements ModelDriven<AMapPoiForm> {
    private AMapPoiForm ampf = new AMapPoiForm();
    private AMapPoiRequest ampr = new AMapPoiRequest();
    private GetPlaceSearchBO getPlaceSearchBO = new GetPlaceSearchBOImpl();
    private AMapPoiBO aMapPoiBO = new AMapPoiBOImpl();
    private AMapAdCodeDAO aMapAdCodeDAO = new AMapAdCodeDAO();

    private String contentType;
    private String fileName;
    private InputStream inputStream;


    // prod set
    public static final String FILE_PATH = "/home/marika/data/amap/";
    // local set
    // private static final String FILE_PATH = "/Users/fan/Documents/MyIDEA/poicollect/poidata/amap/";

    public String queryInit() {

        ampr.setCity(ampf.getCity());
        if (ampf.getSubtype() != null && !"".equals(ampf.getSubtype()))
            ampr.setTypes(ampf.getSubtype());
        else if (ampf.getMidtype() != null && !"".equals(ampf.getMidtype()))
            ampr.setTypes(ampf.getMidtype());
        else
            ampr.setTypes(ampf.getBigtype());

        List<AMapPoiResponse> regionList = aMapPoiBO.getAMapPoi(ampr);

        // 文件存储
        try {
            HSSFWorkbook workbook = getPlaceSearchBO.exportAMapExcel(regionList);
            String fileName = ampr.getCity() + "-" + ampr.getTypes() + ".xls";
            ampf.setFilename(fileName);
            File file = new File(FILE_PATH + fileName);
            if (!file.exists()) {
                if (file.createNewFile())
                    System.out.println("创建新文件");
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ampf.setResponseList(regionList);
        ampf.setCity(ampr.getCity());
        ampf.setTypes(ampr.getTypes());
        ampf.setCount(regionList.size());

        return SUCCESS;
    }



    @Override
    public AMapPoiForm getModel() {
        if (ampf == null)
            ampf = new AMapPoiForm();
        return ampf;
    }

    public String poiDownload() {
        contentType = "application/vnd.ms-excel";
        fileName = ampf.getFilename();
        try {
            inputStream = new FileInputStream(FILE_PATH + fileName);
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
