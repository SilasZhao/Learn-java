package com.lombok;

import lombok.NonNull;
import org.junit.Test;

/**
 * @NotNull
 * 生成非空检查
 */
public class NotNull {
    public void NotNullTest(@NonNull String String1){
        System.out.println(String1);
    }
}
