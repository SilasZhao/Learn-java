package com.lombok;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTest {
    @Test
    public void func(){
        log.error("日志");

    }

}
