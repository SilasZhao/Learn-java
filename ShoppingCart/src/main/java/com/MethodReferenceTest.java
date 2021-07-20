package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class MethodReferenceTest {
    static class Sku{
        private String skuName;
        private Integer skuPrice;
        private Integer getSkuPrice(){
            return this.skuPrice;
        }
        //静态方法
        public static int staticComparePrice(Sku sku1, Sku sku2){
            return sku1.getSkuPrice() - sku2.getSkuPrice();
        }
        public int instanceComparePrice(Sku sku){
            return this.getSkuPrice() - sku.getSkuPrice();

        }
    }
    class PriceComparator{
        //不是静态方法！
        public int instanceComparePrice(Sku sku1, Sku sku2){
            return sku1.getSkuPrice() - sku2.getSkuPrice();
        }
    }
    public void test(){
        List<Sku> skuList = new ArrayList<>();
        skuList.sort((sku1,sku2)-> sku1.getSkuPrice() - sku2.getSkuPrice());
        //class::static method name
        skuList.sort(Sku::staticComparePrice);
        //展开
        skuList.sort((Sku sku1, Sku sku2)->{
            return Sku.staticComparePrice(sku1,sku2);
        });

        //Object :: Object method name
        PriceComparator priceComparator = new PriceComparator();
        skuList.sort(priceComparator::instanceComparePrice);
        //展开
        skuList.sort((Sku sku1, Sku sku2) -> {
            return priceComparator.instanceComparePrice(sku1,sku2);
        });

        //class name :: object method name
        skuList.sort(Sku::instanceComparePrice);
        //展开 用类的话回默认先调他自己
        skuList.sort((Sku object, Sku sku) -> {
            return object.instanceComparePrice(sku);
        });
        //构造方法
        Optional.ofNullable(skuList).orElseGet(ArrayList::new);
    }
}
