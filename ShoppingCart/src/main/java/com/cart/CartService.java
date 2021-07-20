package com.cart;

import java.util.ArrayList;
import java.util.List;

import static com.cart.SkuCategoryEnum.ELECTRONICS;

public class CartService {

    private static List<Sku> cartSkuList = new ArrayList<Sku>(){
        {
            add(new Sku(654032, "无人机",
            4999.00, 1, 4999.00,
                    SkuCategoryEnum.ELECTRONICS));
            add(new Sku(642934, "VR一体机",
            2299.00, 1, 2299.00,
                    SkuCategoryEnum.ELECTRONICS));
            add(new Sku(645321, "纯色衬衫",
                    409.00, 3,
                    1227.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(654327, "牛仔裤",
                    528.00, 1,
                    528.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(675489, "跑步机",
                    2699.00, 1,
                    2699.00, SkuCategoryEnum.SPORTS));

            add(new Sku(644564, "Java编程思想",
                    79.80, 1,
                    79.80, SkuCategoryEnum.BOOKS));

            add(new Sku(678678, "Java核心技术",
                    149.00, 1,
                    149.00, SkuCategoryEnum.BOOKS));

            add(new Sku(697894, "算法",
                    78.20, 1,
                    78.20, SkuCategoryEnum.BOOKS));

            add(new Sku(696968, "TensorFlow进阶指南",
                    85.10, 1,
                    85.10, SkuCategoryEnum.BOOKS));
        }
    };
    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }

    /**
     * version 1.0
     * 只能过滤一个电子类
     * @param cartSkuLiost
     * @return
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuLiost){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList){
            if(ELECTRONICS.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * version 2.0
     * 商品类型过滤商品
     * @param cartSkuList
     * @param category
     * @return
     */
    public static List<Sku> filterSkusByCategory(
            List<Sku> cartSkuList, SkuCategoryEnum category){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList){
            if(category.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * version 3.0
     * 商品类型或总价来过滤商品
     * @param cartSkuaList
     * @param category
     * @param totalPrice
     * @param categoryOrPrice true: 根据商品类型 false：根据商品总价
     * @return
     */
    public static List<Sku> filterSkus(
            List<Sku> cartSkuaList, SkuCategoryEnum category,
            Double totalPrice, Boolean categoryOrPrice){
                List<Sku> result = new ArrayList<Sku>();
                for (Sku sku : cartSkuList){
                    //True, 商品类型
                    if(categoryOrPrice && category.equals(sku.getSkuCategory())//false 商品价格
                    || !categoryOrPrice && sku.getTotalPrice() > totalPrice){
                        result.add(sku);
                    }
                }
                return result;
    }

    /**
     * version 4.0
     * 根据不同的Sku判断标准，对Sku列表进行过滤
     * @param cartSkuList
     * @param predicate - 不同Sku判断标准策略
     * @return
     * 策略模式！
     */
    public static List<Sku> filterSkus(
            List<Sku> cartSkuList, SkuPredicate predicate){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList){
            //根据不同的商品进行不同的判断
            if(predicate.test(sku)){
                result.add(sku);
            }
        }
        return result;
    }
}
