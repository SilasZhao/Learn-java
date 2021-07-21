package com.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 设计一个对外提供服务的接口，支持调用方传入多个账户编号查询账单
 */
public class Case4 {
    @Data
    @AllArgsConstructor
    class Order{
        private Integer orderID;
        private String accountID;
    }

    /**
     * 模拟数据库查询
     * @param accountID
     * @return
     */
    public List<Order> selectFromDB(List<String> accountID){
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orderList.add(
                    new Order(
                            i,accountID.get(i % accountID.size())
                    )
            );

        }
        return orderList;
    }

    public Map<String, List<Order>> queryOrderByAccountIDS(
            List<String> accountIds
    ){
        return Optional.ofNullable(selectFromDB(accountIds))
                .map(List::stream)
                .orElseGet(Stream::empty)
                .collect(Collectors.groupingBy(
                        order -> order.getAccountID()));
    }
    @Test
    public void test(){
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        Map<String, List<Order>> orders =
                queryOrderByAccountIDS(list);
        System.out.println(JSON.toJSONString(orders,true));
    }


}
