package com.marika.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ConnPool {

    private static final Logger LOG = LoggerFactory.getLogger(ConnPool.class);

    private static final SqlSessionFactory POI_DATASOURCE = getFactory("poi");

    private ConnPool() {

    }

    private static SqlSessionFactory getFactory(String env) {
        SqlSessionFactory factory = null;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader, env);
        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error("", e);
                }
            }
        }
        return factory;
    }

    public static SqlSession getSqlSession(String poolName) {
        return POI_DATASOURCE.openSession();
    }

    public static void close(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    public static void rollback(SqlSession session) {
        if (session != null) {
            session.rollback();
        }
    }

    public static SqlSession getSqlSession() {
        return POI_DATASOURCE.openSession();
    }
}