package com.lombok;

import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * Getter 注解
 * 为属性生成方法
 */
@AllArgsConstructor
public class GetterTest {
    @Getter(lazy = true)
    private final String field1 = "zsy";
    @Getter(
            value = AccessLevel.PRIVATE,
            onMethod_= {@NotNull}
    )
    private String field2;
}
