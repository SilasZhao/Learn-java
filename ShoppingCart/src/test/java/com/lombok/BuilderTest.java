package com.lombok;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class BuilderTest {
    //静态属性
    //静态属性不能赋值
   private static String staticField;
   //final属性
   private final String finalField;
   //已经初始化的final属性
    //初始化的final字段不能赋值
   private final String initFinalField = "初始化过的finnal字段";
   //普通属性
   private String field = "普通属性";

   @Singular
   private List<String> listFields;

    public static void main(String[] args) {
        BuilderTest builderTest = BuilderTest
                //创建一个可以链式赋值的对象
                .builder()
                .finalField("设定finalField字段")
                 .field("设定field字段")
                .listField("1")
                .listField("2")
                //build方法来创建对象，此对象不可变！！
                .build();
        System.out.println(JSON.toJSONString(builderTest,true));
    }

}
