package com.lombok;

import lombok.var;

import java.util.ArrayList;

/**
 * @Val
 * 弱语言变量
 * 作用范围：本地方法
 */
public class ValTest {
    private ValTest(){
        var field = "zhaosongyan";
        var list = new ArrayList<String>();
        list.add("zh");
    }
}
