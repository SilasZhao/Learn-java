package com.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * The use of Lists and sets
 */
public class SetsTest {
    /**
     * Sets工具类的常用方法
     * 并集/交集/差集/分解集合中的所有子集/求两个集合的笛卡尔积
     *
     * List 工具类的常用方式
     * 反转/拆分
     */
    private static final Set set1 =
            Sets.newHashSet(1,2);
    private static final Set set2 =
            Sets.newHashSet(4,5);
    @Test
    public void union(){
        Set<Integer> set = Sets.union(set1,set2);
        System.out.println(set);
    }
    @Test
    public void intersection(){
        Set<Integer> set = Sets.intersection(set1,set2);
        System.out.println(set);
    }

    /**
     * 差集：元素属于A但不属于B
     */
    @Test
    public void difference(){
        Set<Integer> set = Sets.difference (set1,set2);
        System.out.println(set);
        /**
         * 相对差集：属于A而且不属于B 或者 属于B而且不属于A
         */

        System.out.println(Sets.symmetricDifference(set1,set2));
    }

    /**
     * Find powerSet
     */
    @Test
    public void powerSet(){
        Set<Set<Integer>> powerSet = Sets.powerSet(set1);
        System.out.println(JSON.toJSONString(powerSet,true));
    }
    @Test
    public void cartesionProduct(){
        Set<List<Integer>> product =
                Sets.cartesianProduct(set1,set2);
        System.out.println(JSON.toJSONString(product ));
    }
    @Test
    public void partition(){
        List<Integer> list =
                Lists.newArrayList(1,2,3,4,5,6,7);
        List<List<Integer>> partition = Lists.partition(list,3);
        System.out.println(JSON.toJSONString(partition));
    }
    @Test
    public void reverse(){
        List<Integer> list = Lists.newLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(Lists.reverse(list));
    }

}
