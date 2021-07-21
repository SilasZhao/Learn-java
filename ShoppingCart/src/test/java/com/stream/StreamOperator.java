package com.stream;

import com.alibaba.fastjson.JSON;
import lamdba.cart.CartService;
import lamdba.cart.Sku;
import lamdba.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

/**
 * 流的各种操作
 */
public class StreamOperator {
    List<Sku> list;

    @Before
    public void init(){
        list = CartService.getCartSkuList();
    }

    /**
     * 滤掉不符合断言判断的数据
     */
    @Test
    public void filterTest(){
        list.stream()
                //filter
                .filter(sku -> SkuCategoryEnum.BOOKS
                        .equals(sku.getSkuCategory()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }

    /**
     * map turns one element into another element
     */
    @Test
    public void mapTest(){
        list.stream()
                //map
                .map(sku -> sku.getSkuName())
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }

    /**
     * Flatmap: 将一个对象转化成一个流
     */
    @Test
    public void flatMapTest(){
        list.stream()
                //flatmap
                .flatMap(sku -> Arrays.stream(
                        sku.getSkuName().split("")))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
     }

    /**
     * Peek: 对流进行遍历操作，但因为他是中间操作，所以不会销毁流
     */
    @Test
     public void peek(){
         list.stream()
                 //peek
                 .peek(sku -> System.out.println(sku.getSkuName()))
                 //这里peek和foreach中的元素交替执行，因为 1) peek 是无状态操作 2） stream只有在终端操作时才会从上到下依次遍历每个元素
                 .forEach(item ->
                         System.out.println(
                                 JSON.toJSONString(
                                         item,true)));
     }
    /**
     * sort：自然排序， 或制定排序规则
     */
    @Test
    public void sortTest(){
        list.stream()
                //peek先，foreach后
                .peek(sku -> System.out.println(sku.getSkuName()))
                //sorted 这里sorted是有状态操作，所以peek中的数据会先汇总，然后再再foreach中被操作。
                .sorted(Comparator.comparing(Sku::getTotalPrice))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }

    /**
     * distinct:对流元素去重，有状态操作
     */

    @Test
    public void distinctTest(){
        list.stream()
                .map(sku -> sku.getSkuCategory())
                .distinct()
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }

    /**
     * skip: 跳过前n条记录,有状态操作
     */

    @Test
    public void skipTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getSkuCategory))
                .skip(3)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }
    /**
     * limit：阶段前n条
     */
    @Test
    public void limitTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getSkuCategory))
                .limit(3)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item,true)));
    }
    /**
     * allMatch: 终端操作，短路操作,所有元素满足条件，返回true
     */
    @Test
    public void allMathchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     * anyMatch: 终端操作，短路操作，任何元素满足条件，返回true
     */
    @Test
    public void anyMathchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }
    /**
     * noneMatch: 终端操作，短路操作，所有元素不满足，返回true
     */
    @Test
    public void noneMathchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);
        System.out.println(match);
    }

    /**
     *  max：找到最大值, 非短路操作
     */
    @Test
    public void maxTest(){
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     *  min：找到最小值， 非短路操作
     */
    @Test
    public void minTest(){
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     *  count：获取元素总数， 非短路操作
     */
    @Test
    public void countTest(){
        Long l = list.stream()
                .count();
        System.out.println(l);
    }
}

