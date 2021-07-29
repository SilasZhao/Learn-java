package com.example.demo.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeStringConverter implements Converter<LocalDateTime> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;

    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {

        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public CellData convertToExcelData(LocalDateTime localDateTime,
                                       ExcelContentProperty excelContentProperty,
                                       GlobalConfiguration globalConfiguration) throws Exception {

        if(excelContentProperty == null ||
                excelContentProperty.getDateTimeFormatProperty() == null){
            //默认格式化格式
            return new CellData(DateTimeFormatter.ISO_LOCAL_DATE_TIME
            .format(localDateTime));
        }else{
            //自定义格式化格式
            return new CellData(DateTimeFormatter.ofPattern(
                    excelContentProperty
                            .getDateTimeFormatProperty()
                            .getFormat())
                    .format(localDateTime));

        }
    }
}
