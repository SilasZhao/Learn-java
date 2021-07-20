package com.stream;

import com.alibaba.fastjson.JSON;
import com.cart.CartService;
import com.cart.Sku;
import com.cart.SkuCategoryEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 对比原始集合操作和stream集合操作
 */
public class StreamVs {
    /**
     * 1购物车中有什么商品
     * 2图书类的商品都可买
     * 3其余商品买两个最贵的
     * 4只需要两件商品的名称和总价
     */
    /**
     * 以原始集合操作实现
     */
    @Test
    public void oldCartHandler(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        /**
         * print all the items
         */
        for (Sku sku: cartSkuList){
            System.out.println(JSON.toJSONString(sku,true));
        }
        /**
         * filter out all the books
         */
        List<Sku> notBooksSkuList = new ArrayList<>();
        for (Sku sku: cartSkuList){
            if(!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())){
                notBooksSkuList.add(sku);
            }
        }
        /**
         * sort the list
         */
        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                if (o1.getSkuPrice() > o2.getSkuPrice()){
                    return -1;
                }else if (o1.getSkuPrice() < o2.getSkuPrice()) {
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        /**
         * get top 2
         */
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBooksSkuList.get(i));
        }
        /**
         * get the sum
         */
        Double money = 0.0;
        for (Sku sku :
                top2SkuList) {
            money += sku.getTotalPrice();
        }
        /**
         * get name
         */
        List<String> resultSkuNameList = new ArrayList<>();
        for (Sku sku :
                top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }
        /**
         * sout
         */
        System.out.println(
                JSON.toJSONString(resultSkuNameList,true)
        );
        System.out.println("sum: " + money);
    }
    /**
     * 用Stream
     */
    public void newCartHandler(){
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));

        List<String> resultSkuNameList =
                CartService.getCartSkuList().stream()
                /**
                 * 1print item name
                 */
                .peek(sku -> System.out.println(JSON.toJSONString(sku,true)))
                /**
                 * filters out every books type
                 */
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                /**
                 * sort
                 */
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                /**
                 * Top2
                 */
                .limit(2)
                /**
                 * get the sum
                 */
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                /**
                 * get names
                 */
                .map(sku -> sku.getSkuName())
                /**
                 * get final list
                 */
                .collect(Collectors.toList());
        System.out.println(
                JSON.toJSONString(resultSkuNameList,true)

        );
        System.out.println("sum: " + money.get());
    }
}
