package com.zlhj.pm.excel.demo.write;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author tzm
 * @Date 2020/4/17 1:24
 * @Version 1.0
 */
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String str;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略字段
     */
    @ExcelIgnore
    private String ignore;
}
