package com.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;


public class Stoke {
    @Data
    @AllArgsConstructor
    class Trade{
        private BigDecimal price;
        private LocalDateTime time;
        private Integer count;
        private String type;
    }
    List<Trade> trades;
    @Before
    public void init(){
        trades = new ArrayList<>();
        trades.add(new Trade(new BigDecimal( 100),
                LocalDateTime.now().plusSeconds(1),
                500,"机构"));
        trades.add(new Trade(new BigDecimal(101),
                LocalDateTime.now().plusSeconds(2),
                1, "个人"));
        trades.add(new Trade(new BigDecimal(101),
                LocalDateTime.now().plusSeconds(1),
                1, "个人"));
        trades.add(new Trade(new BigDecimal(100),
                LocalDateTime.now().plusSeconds(1),
                500, "个人"));
        trades.add(new Trade(new BigDecimal(100),
                LocalDateTime.now().plusSeconds(0),
                2, "个人"));
        trades.add(new Trade(new BigDecimal(100),
                LocalDateTime.now().plusSeconds(0),
                100, "机构"));

    }
    @Test
    public void sortTrade(){
        System.out.println(JSON.toJSONString(trades,true));
        List<Trade> sorted = trades.stream()
                /*
                .sorted((Trade trade1, Trade trade2) -> {
                    if(trade1.getPrice().compareTo(trade2.getPrice()) == 1){
                        return 1;
                    }else if(trade1.getPrice().compareTo(trade2.getPrice()) == -1){
                        return -1;
                    }else{
                        if (trade1.getTime().compareTo(trade2.getTime()) == 1){
                            return 1;
                        }else if (trade1.getTime().compareTo(trade2.getTime()) == -1){
                            return -1;
                        }else{
                            if(trade1.getCount().compareTo(trade2.getCount()) == 1){
                                return 1;
                            }else if(trade1.getCount().compareTo(trade2.getCount()) == -1){
                                return -1;
                            }else{
                                if (trade1.getType().equals(trade2.getType())){
                                    return 0;
                                }else if(trade1.getType().equals("机构")){
                                    return 1;
                                }else{
                                    return 0;
                                }
                            }
                        }
                    }
                })
                */
                .sorted(
                        Comparator.comparing(
                                Trade::getPrice,
                                Comparator.reverseOrder()
                        )
                                .thenComparing(Trade::getTime)
                                .thenComparing(Trade::getCount,
                                        Comparator.reverseOrder())
                                //要排序的字段值
                                .thenComparing(Trade::getType,
                                        //排序规则
                                        (type1, type2) -> {
                                            if ("机构".equals(type1) && "个人".equals(type2)) {
                                                //type1在先，type2在后
                                                return -1;
                                            } else if ("个人".equals(type1) && "机构".equals(type2)) {
                                                return 1;
                                            } else {
                                                return 0;
                                            }
                                        })
                )
                .collect(Collectors.toList());
        System.out.println("最后结果： ");
        System.out.println(JSON.toJSONString(sorted,true));
    }
}
