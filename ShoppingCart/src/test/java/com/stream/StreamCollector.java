package com.stream;

import com.alibaba.fastjson.JSON;
import lamdba.cart.CartService;
import lamdba.cart.Sku;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常见预定义收集器的使用
 */
public class StreamCollector {
    /**
     *
     */
    @Test
    public void toList(){
        List<Sku> list = CartService.getCartSkuList();
        List<Sku> result = list.stream()
                .filter(sku -> sku.getTotalPrice() > 100)
                .collect(Collectors.toList());
        System.out.println(
                JSON.toJSONString(result,true)
        );
    }
    @Test
    public void group(){
        List<Sku> list = CartService.getCartSkuList();
        //Map<分组条件，结果集合>
        Map<Object,List<Sku>> group = list.stream()
                .collect(
                        Collectors.groupingBy(
                                sku -> sku.getSkuCategory()
                        ));


        System.out.println(
                JSON.toJSONString(group,true)
        );
    }
    @Test
    public void partition(){
        List<Sku> list = CartService.getCartSkuList();
        Map<Boolean,List<Sku>> partition = list.stream()
                .collect(Collectors.partitioningBy(
                        sku -> sku.getTotalPrice() > 100
                ));
        System.out.println(
                JSON.toJSONString(partition,true)
        );
    }
}
