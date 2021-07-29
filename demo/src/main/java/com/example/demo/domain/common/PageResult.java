package com.example.demo.domain.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 7591526595060260953L;
    /**
     * 当前页号
     */
    private Integer pageNo;

    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pageNum;

    /**
     * 动态的内容
     */
    private T data;

}
