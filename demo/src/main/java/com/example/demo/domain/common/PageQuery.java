package com.example.demo.domain.common;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID = 8734330896245495455L;
    /**
     * 页数
     */
    @NotNull(message = "不能为空")
    @Min(value = 1, message = "页数必须为正")
    @Max(value = 100, message = "页数小于100！")
    private Integer paveNo = 1;
    /**
     * 每页数
     */
    private Integer pageSize = 20;
    @Valid
    @NotNull(message = "动态查询条件不能为空")
    private T query;
}
