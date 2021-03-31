package com.tx.sdream.util.collect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTest {

    public void print(boolean obj) {
        log.info(obj + "");
    }

    public void print(String obj) {
        log.info(obj);
    }
}
