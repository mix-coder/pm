package com.zlhj.pm;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.zlhj.pm.mapper")
public class PmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmApplication.class, args);
    }

}
