package com.zlhj.pm.excel.demo.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author tzm
 * @Date 2020/4/17 17:47
 * @Version 1.0
 */
@Data
public class ConverterData {
    @ExcelProperty(value = "字符串标题",converter = CustomStringStringConverter.class)
    private String str;
    /**
     * 写入Excel时，用年月日的格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("日期标题")
    private Date date;
    /**
     * 写入Excel时，用百分比表示
     */
    @NumberFormat("#.##%")
    @ExcelProperty(value = "数字标题")
    private Double doubleData;
}
