package com.guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {
    @Test
    public void test() throws Throwable {
        /**
         * 三种创建optional对象方式
         */
        /**
         * 创建空的Optional对象
         */
        Optional.empty();
        /**
         * 使用非null值创建Optional对象
         */
        Optional.of("2");
        /**
         * 使用任意值创建Optional对象
         */
        Optional optional = Optional.ofNullable("@");
        /**
         * 判断是否引用缺失(建议不使用)
         */
        optional.isPresent();
        /**
         * 当optional中引用存在时执行方法
         * 类似的方法：map filter flatmap
         */
        optional.ifPresent(System.out::println);
        /**
         * 当optional引用缺失时执行
         */
        optional.orElse("引用缺失");
        optional.orElseGet(() -> {
            return "自定义引用缺失";
        });
        optional.ofNullable(null).orElseThrow(() -> {
            throw new RuntimeException("自定义错误");
        });
    }
    public static void stream(List<String> list){
        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);

    }
    @Test
    public void k(){
        stream(null);
    }
}
