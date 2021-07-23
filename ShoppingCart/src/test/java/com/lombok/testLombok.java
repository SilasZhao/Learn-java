package com.lombok;

import org.junit.Test;

public class testLombok {
    @Test
    public void sout(){
        GetterTest g = new GetterTest("2");
        System.out.println(g.getField1());
    }
}
