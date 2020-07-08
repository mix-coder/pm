package com.zlhj.pm.excel.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zlhj.pm.excel.demo.write.ComplexHeadData;
import com.zlhj.pm.excel.demo.write.ConverterData;
import com.zlhj.pm.excel.demo.write.DemoData;
import com.zlhj.pm.excel.demo.write.IndexData;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author tzm
 * @Date 2020/4/17 1:31
 * @Version 1.0
 */
public class ExcelTest {
    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite(){
        // 写法1
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"simpleWrite"+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为简单模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName,DemoData.class)
                .sheet("简单模板")
                .doWrite(data());
        // 写法2
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
    /**
     * 根据参数只导出指定列
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 根据自己或者排除自己需要的列
     * <p>
     * 3. 直接写即可
     *
     * @since 2.1.1
     */
    @Test
    public void excludeOrIncludeWrite(){
        // 排除某列
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"exclude"+System.currentTimeMillis()+".xlsx";
        // 根据用户传入字段，假设我们要忽略date
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("date");
        EasyExcel.write(fileName,DemoData.class)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("排除字段模板")
                .doWrite(data());
        // 只导出某列
        String fileName1 = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"include"+System.currentTimeMillis()+".xlsx";
        Set<String> includeFiledNames = new HashSet<>();
        includeFiledNames.add("date");
        EasyExcel.write(fileName1,DemoData.class)
                .includeColumnFiledNames(includeFiledNames)
                .sheet("只包含字段模板")
                .doWrite(data());

    }
    /**
     * 指定写入的列
     * <p>1. 创建excel对应的实体对象 参照{@link IndexData}
     * <p>2. 使用ExcelProperty注解指定写入的列
     * <p>3. 直接写即可
     */
    @Test
    public void indexWrite(){
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"indexWrite"+System.currentTimeMillis()+".xlsx";
        EasyExcel.write(fileName, IndexData.class)
                .sheet("指定写入的列模板")
                .doWrite(data());
    }
    /**
     * 复杂头写入
     * <p>1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>2. 使用ExcelProperty注解指定复杂的头
     * <p>3. 直接写即可
     */
    @Test
    public void complexHeadWrite(){
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"complexHeadWrite"+System.currentTimeMillis()+".xlsx";
        EasyExcel.write(fileName,ComplexHeadData.class)
                .sheet("复杂标题模板")
                .doWrite(data());
    }
    /**
     * 重复多次写入(写到单个或者多个Sheet)
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>
     * 2. 使用ExcelProperty注解指定复杂的头
     * <p>
     * 3. 直接调用二次写入即可
     */
    @Test
    public void repeatedWrite() {
        // 方法1 如果写到同一个sheet
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"repeatedWrite_"+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        // 方法2 如果写到不同的sheet 同一个对象
        String fileName1 = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"repeatedWrite1_"+System.currentTimeMillis()+".xlsx";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName1, DemoData.class).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            writeSheet = EasyExcel.writerSheet(i, "模板"+i).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        // 方法3 如果写到不同的sheet 不同的对象
        String fileName2 = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"repeatedWrite2_"+System.currentTimeMillis()+".xlsx";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName2).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            writeSheet = EasyExcel.writerSheet(i, "模板"+i).head(DemoData.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DemoData> data = data();
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
    /**
     * 日期、数字或者自定义格式转换
     * <p>1. 创建excel对应的实体对象 参照{@link ConverterData}
     * <p>2. 使用{@link ExcelProperty}配合使用注解{@link DateTimeFormat}、{@link NumberFormat}或者自定义注解
     * <p>3. 直接写即可
     */
    @Test
    public void converterWrite() {
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"converterWrite_"+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ConverterData.class).sheet("自定义模板").doWrite(data());
    }
    /**
     * 根据模板写入
     * <p>1. 创建excel对应的实体对象 参照{@link IndexData}
     * <p>2. 使用{@link ExcelProperty}注解指定写入的列
     * <p>3. 使用withTemplate 写取模板
     * <p>4. 直接写即可
     */
    @Test
    public void templateWrite() {
        String templateFileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"demo.xlsx";
        String fileName = "C:\\Users\\37379\\Desktop\\新建文件夹\\"+"templateWrite_"+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class)
                .withTemplate(templateFileName)
                .sheet()
                .doWrite(data());
    }

    private List<DemoData> data(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setStr("字符串"+i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
