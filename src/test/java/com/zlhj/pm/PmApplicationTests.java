package com.zlhj.pm;

import com.zlhj.pm.service.impl.IMailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.File;

@SpringBootTest
class PmApplicationTests {

    @Autowired
    private IMailServiceImpl mailService;

    @Test
    void contextLoads() {
        String[] toUsers = new String[2];
        toUsers[0] = "daixin@linkfin-tech.com";
        toUsers[1] = "tangzhiming@linkfin-tech.com";
        String cc = "fanjie99@linkfin-tech.com";
        String filePath = "C:"+ File.separator +"Users"+File.separator+"37379"+File.separator+"Downloads"+File.separator+"test.xlsx";
//        String filePath = "C:"+ File.separator +"Users"+File.separator+"37379"+File.separator+"Desktop"+File.separator+"测试环境地址20200212.xlsx";
        try {
            mailService.sendAttachmentsMail(toUsers,"测试邮件","Java测试邮件带附件",filePath,cc);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }

}
