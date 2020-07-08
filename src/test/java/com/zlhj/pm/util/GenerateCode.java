package com.zlhj.pm.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 * @author tzm
 * @version 1.0
 * @since 2020/7/8 11:58
 */
public class GenerateCode {

    public static void main(String[] args) {
        // 构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("tzm");
        gc.setOpen(false);
        gc.setFileOverride(false); // 是否覆盖
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.TIME_PACK);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:postgresql://localhost:5432/zlhj_ishare");
        dsc.setSchemaName("public");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("zlhj_ishare");
        dsc.setPassword("zlhj#1234");
        dsc.setDbType(DbType.POSTGRE_SQL);
        mpg.setDataSource(dsc);
        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.zlhj.pm");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("article_info"); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充配置
//        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
//        TableFill gmtModified = new TableFill("gmt_modified",FieldFill.INSERT_UPDATE);
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(gmtCreate);
//        tableFills.add(gmtModified);
//        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
        mpg.setStrategy(strategy);
        mpg.execute(); //执行
    }
}
