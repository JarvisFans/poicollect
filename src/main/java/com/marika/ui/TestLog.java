package com.marika.ui;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLog {
        public static void main(String[] arge){
            log.debug("taiyonghai debug");
            log.info("taiyonghai info");
            log.error("taiyonghai error");
            log.warn("taiyonghai warn");
        }

    public void log(){
        log.debug("taiyonghai debug");
        log.info("taiyonghai info");
        log.error("taiyonghai error");
        log.warn("taiyonghai warn");
    }
}
