package com.marika.dao;

import com.marika.db.ConnPool;
import com.marika.mapper.AMapAdCodeOpt;
import com.marika.po.AMapAdCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
public class AMapAdCodeDAO {
    public List<AMapAdCode> queryAdCode() throws Exception {
        SqlSession session = null;
        List<AMapAdCode> ret;
        try{
            session = ConnPool.getSqlSession();
            AMapAdCodeOpt opt = session.getMapper(AMapAdCodeOpt.class);
            ret = opt.queryAdCode();
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

    public List<AMapAdCode> queryAdCodeWithProvince() throws Exception {
        SqlSession session = null;
        List<AMapAdCode> ret;
        try{
            session = ConnPool.getSqlSession();
            AMapAdCodeOpt opt = session.getMapper(AMapAdCodeOpt.class);
            ret = opt.queryAdCodeWithProvince();
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

    public List<AMapAdCode> queryAdCodeByProvince(String cityPrefix) throws Exception {
        SqlSession session = null;
        List<AMapAdCode> ret;
        try{
            session = ConnPool.getSqlSession();
            AMapAdCodeOpt opt = session.getMapper(AMapAdCodeOpt.class);
            ret = opt.queryAdCodeByProvince(cityPrefix);
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

    public int queryAdCodeTotal() throws Exception {
        SqlSession session = null;
        int ret;
        log.info("Dao");
        try{
            session = ConnPool.getSqlSession();
            AMapAdCodeOpt opt = session.getMapper(AMapAdCodeOpt.class);
            ret = opt.queryAdCodeTotal();
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