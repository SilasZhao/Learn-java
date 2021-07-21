package com.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 规约与汇总操作
 */
public class ReduceAndCollectTest {
    @Test
    public void reduceTest(){
        /**
         * 订单对象
         */
        @Data
        @AllArgsConstructor
        class Order{
            /**
             * 订单编号
             */
            private Integer id;
            /**
             * 商品数量
             */
            private Integer productCount;
            /**
             * 消费总金额
             */
            private Double totalAmount;
        }
        /**
         * preparing data
         */
        ArrayList<Order> list = new ArrayList<>();
        list.add(new Order(1,2,25.12));
        list.add(new Order(2,5,257.23));
        list.add(new Order(3,3,23332.12));
        /**
         * 以前的方式：
         * 1，计算商品数量
         * 2，计算消费总金额
         */
        /**
         * 汇总
         */
        Order order = list.stream()
                //开启并行模式
                .parallel()
                .reduce(//init
                        new Order(0,0,0.0),
                        //计算逻辑
                        (Order order1, Order order2) -> {
                            System.out.println("执行 计算逻辑 方法！！");
                            int productCount =
                                    order1.getProductCount()
                                    + order2.getProductCount();

                            double totalAmount =
                                    order1.getTotalAmount()
                                    + order2.getTotalAmount();
                            return new Order(0,productCount,totalAmount);
                            //并行合并方式
                        },(Order order1, Order order2) -> {
                            System.out.println("执行 合并 方法！！");
                            int productCount =
                                    order1.getProductCount()
                                            + order2.getProductCount();
                            double totalAmount =
                                    order1.getTotalAmount()
                                            + order2.getTotalAmount();
                            return new Order(0,productCount,totalAmount);

                        });
        System.out.println(JSON.toJSONString(order,true));
    }
    @Test
    public void collectTest(){
        @Data
        @AllArgsConstructor
        class Order{
            /**
             * 订单编号
             */
            private Integer id;
            /**
             * 商品数量
             */
            private Integer productCount;
            /**
             * 消费总金额
             */
            private Double totalAmount;
            /**
             * 用户账号
             */
            private String account;
        }
        /**
         * preparing data
         */
        ArrayList<Order> list = new ArrayList<>();
        list.add(new Order(1,2,25.12, "zhang"));
        list.add(new Order(2,5,257.23,"zhang"));
        list.add(new Order(3,3,23332.12,"li"));
        /*
            Map<用户账号，订单（数量和金额）>
         */
        HashMap<String, Order> collect = list.stream()
                .parallel()
                .collect(() -> {
                            System.out.println("执行 初始化容器 操作！！");
                    return new HashMap<String, Order>();
                },
                        (HashMap<String, Order> map, Order neworder) -> {
                            System.out.println("执行 新元素添加到容器 操作！！！");
                            /*
                            新元素的account已经再map中存在了/不存在
                             */
                            String account = neworder.getAccount();
                            if (map.containsKey(account)){
                                Order order = map.get(account);
                                order.setProductCount(neworder.getProductCount()
                                                        + order.getProductCount());
                                order.setTotalAmount(
                                        neworder.getTotalAmount()
                                        + order.getTotalAmount()
                                );
                            }else{
                                //不存在
                                map.put(account, neworder);
                            }
                        }, (HashMap<String, Order> map1, HashMap<String, Order> map2) -> {
                            System.out.println("执行 并行结果合并 操作！！");
                            map2.forEach((key,value) -> {
                                /*
                                    一定要用map1做合并,因为最后collect返回的map1
                                    如果map2中的key在map1中存在，
                                    那就把map1中的value和map2中的value用第三个参数中的方法合并起来并返回到map1中，
                                    若map2中的key在map1中不存在，
                                    就把这个key和value push到map1中
                                 */
                                map1.merge(key,value,(order1, order2) -> {
                                    //key 存在时更新value
                                    return new Order(0,order1.getProductCount()
                                                                + order2.getProductCount(),
                                                    order1.getTotalAmount()
                                                                +order2.getTotalAmount(),
                                                        key);
                                });
                            });
                        });
        System.out.println(JSON.toJSONString(collect,true));

    }
}
