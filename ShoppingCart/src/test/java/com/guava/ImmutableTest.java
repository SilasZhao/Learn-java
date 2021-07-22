package com.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;

public class ImmutableTest {
    public static void test(List<Integer> list){
        list.remove(0);
    }
    public void immutable(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ImmutableSet immutableSet = ImmutableSet.of(1,2,3);
        ImmutableSet.copyOf(list);
        ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(2,3))
                .add(4)
                .build();
    }
}
