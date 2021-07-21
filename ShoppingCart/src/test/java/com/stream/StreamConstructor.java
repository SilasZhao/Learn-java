package com.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream的四种构建方式
 */
public class StreamConstructor {
    /**
     * 通过数值构建流
     */
    @Test
    public void streamFromValue(){
        Stream stream = Stream.of(1,2,3,4,5);
        stream.forEach(System.out::println);
    }

    /**
     * 通过数组构建流
     */
    @Test
    public void streamFromArray(){
        int[] numbers = {1,2,34,45,5};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    /**
     * 通过文件
     * @throws IOException
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stream = Files.lines(
                Paths.get("/Users/admin/Desktop/Learn-java/ShoppingCart/src/test/java/com/stream/StreamConstructor.java"));
        stream.forEach(System.out::println);
    }
    /**
     * 通过函数
     */
    @Test
    public void streamFromFunction(){
        //Stream stream = Stream.iterate(0, n -> n+2);
        Stream stream = Stream.generate(Math::random);
        stream
                .limit(5)
                .forEach(System.out::println);


    }
}
