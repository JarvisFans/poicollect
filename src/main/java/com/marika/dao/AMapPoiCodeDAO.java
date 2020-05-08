package com.marika.dao;

import com.marika.db.ConnPool;
import com.marika.mapper.AMapPoiCodeOpt;
import com.marika.po.AMapPoiCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
public class AMapPoiCodeDAO {
    public List<AMapPoiCode> queryPoiTypeList(String prefix) throws Exception {
        SqlSession session = null;
        List<AMapPoiCode> ret;
        try{
            session = ConnPool.getSqlSession();
            AMapPoiCodeOpt opt = session.getMapper(AMapPoiCodeOpt.class);
            ret = opt.getPoiTypeListByPrefix(prefix);
        } catch (Exception e) {
            log.error("SQL Exception:", e);
            if (session != null)
                session.rollback();
            throw new Exception(e);
        } finally {
            ConnPool.close(session);
        }
        return ret;
    }
}
