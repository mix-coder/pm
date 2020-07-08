package com.zlhj.pm.excel.demo.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author tzm
 * @Date 2020/4/17 15:15
 * @Version 1.0
 */
@Data
public class IndexData {
    @ExcelProperty(value = "字符串标题", index = 0)
    private String str;
    @ExcelProperty(value = "日期标题", index = 1)
    private Date date;
    /**
     * 这里设置3 会导致第二列空的
     */
    @ExcelProperty(value = "数字标题", index = 3)
    private Double doubleData;
}
