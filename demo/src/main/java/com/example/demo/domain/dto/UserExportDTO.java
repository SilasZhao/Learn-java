package com.example.demo.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.example.demo.util.InsertValidationGroup;
import com.example.demo.util.LocalDateTimeStringConverter;
import com.example.demo.util.UpdateValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class UserExportDTO implements Serializable{

    private static final long serialVersionUID = 7902790373805548729L;

    @ExcelProperty(value="用户名")
    private String username;

    @ExcelProperty(value= "年龄")
    private Integer age;

    @ExcelProperty(value = "版本号")
    private Long version;

    @ExcelProperty(value = "创建时间",
            converter = LocalDateTimeStringConverter.class)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒")
    private LocalDateTime created;
}
