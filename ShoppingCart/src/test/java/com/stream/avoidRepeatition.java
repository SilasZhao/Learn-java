package com.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class avoidRepeatition {
    @Data
    @AllArgsConstructor
    class TagReqDTO{
        private String name;
        private Integer age;
    }
    List<String> TagListFromDB;
    List<TagReqDTO> tagListFromReq;
    @Before
    public void init(){
        TagListFromDB = new ArrayList<>();
        TagListFromDB.add("赵六");
        TagListFromDB.add("李四");
        TagListFromDB.add("王五");
        tagListFromReq = new ArrayList<>();
        tagListFromReq.add(new TagReqDTO("张三",10));
        tagListFromReq.add(new TagReqDTO("李四",30));
        tagListFromReq.add(new TagReqDTO("王五",11));
    }
    @Test
    public void distinctTag(){
        tagListFromReq.stream()
                //如果（）中的数据是true，则该数据不过滤
                .filter(tag -> !TagListFromDB.contains(tag.getName()))
                //使用equals对元素进行比较
                .distinct()
                .forEach(System.out::println);


    }






}



