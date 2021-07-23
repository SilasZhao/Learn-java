package com.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SetterTest {
    @Setter
    private String field1;
    @Setter(
            value = AccessLevel.PRIVATE,
            onMethod_={@NotNull}
    )
    private String field2;
}
