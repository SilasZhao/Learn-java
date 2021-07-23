package com.lombok;


import lombok.Setter;
import org.junit.Test;

@lombok.ToString(
        includeFieldNames = false,
        //exclude = {"field1"},
        doNotUseGetters = false
)

public class ToString {
    @Setter
    private String field1;
    @Setter
    private String field2;
    public String getField2(){
        System.out.println("调用");
        return this.field2;
    }
    @Test
    public void test(){
        ToString toStringTest = new ToString();
        toStringTest.setField1("z");
        toStringTest.setField2("sy");
        System.out.println(toStringTest.toString());
    }
}
