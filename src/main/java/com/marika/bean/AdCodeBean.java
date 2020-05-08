package com.marika.bean;

import com.marika.dao.AMapAdCodeDAO;
import com.marika.po.AMapAdCode;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author felix
 */
@Setter

public class AdCodeBean {
    private List<AMapAdCode> adCodeList;
    private List<AMapAdCode> adCodeWithProvinceList;
    private List<AMapAdCode> adCodeByProvinceList;
    private String cityPrefix;
    private AMapAdCodeDAO adCodeDAO = new AMapAdCodeDAO();

    public List<AMapAdCode> getAdCodeList() throws Exception {
        return adCodeDAO.queryAdCode();
    }

    public List<AMapAdCode> getAdCodeWithProvinceList() throws Exception {
        return adCodeDAO.queryAdCodeWithProvince();
    }

    public List<AMapAdCode> getAdCodeByProvinceList() throws Exception {
        // 删除结尾为00和01的无用项
        List<AMapAdCode> list = adCodeDAO.queryAdCodeByProvince(cityPrefix);
        List<AMapAdCode> list2 = new ArrayList<>();
        for (AMapAdCode code : list) {
            System.out.println(code.getAdcode());
            if(String.valueOf(code.getAdcode()).endsWith("00") || String.valueOf(code.getAdcode()).endsWith("01")){
                // System.out.println(code.getAdcode());
                // list.remove(code);
            } else{
                list2.add(code);
            }
        }
        return list2;
    }

    public String getCityPrefix() {
        return cityPrefix;
    }
}
