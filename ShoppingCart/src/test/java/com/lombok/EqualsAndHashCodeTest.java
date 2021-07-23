package com.lombok;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode(
        exclude = {"field1"}
)
public class EqualsAndHashCodeTest {
    private String field1;
    private String field2;
}
