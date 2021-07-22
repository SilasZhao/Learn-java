package com.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.PrimitiveSink;
import org.junit.Test;

public class BloomFileterTest {
    @Test
    public void bloomFilter(){
        BloomFilter bloomFilter = BloomFilter.create(
                //将任意类型数据转化为Java基础类型，默认转换为byte数组
                (Integer from, PrimitiveSink primitiveSink) ->
                    primitiveSink.putInt(from),
            //预计插入的元素总数
            10000L,
                //误判率
                0.1
        );
        //像不拢过滤器中添加元素
        for (int i = 0; i < 10000; i++) {
            bloomFilter.put(i);
        }
        boolean might = bloomFilter.mightContain(66666);
        System.out.println("是否存在？" + might);
    }
}
