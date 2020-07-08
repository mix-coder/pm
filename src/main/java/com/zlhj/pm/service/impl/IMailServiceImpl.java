package com.zlhj.pm.service.impl;

import com.zlhj.pm.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class IMailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

//    @Override
//    public void sendSimpleMail(String[] to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//        mailSender.send(message);
//    }

    @Override
    public void sendSimpleMail(String[] to, String subject, String content, String... cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

//    @Override
//    public void sendHtmlMail(String[] to, String subject, String content) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        mailSender.send(message);
//    }

    @Override
    public void sendHtmlMail(String[] to, String subject, String content, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

//    @Override
//    public void sendAttachmentsMail(String[] to, String subject, String content, String filePath) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        FileSystemResource file = new FileSystemResource(new File(filePath));
//        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//        helper.addAttachment(fileName, file);
//        mailSender.send(message);
//    }

    @Override
    public void sendAttachmentsMail(String[] to, String subject, String content, String filePath, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content, true);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        helper.addAttachment(fileName, file);
        mailSender.send(message);
    }

//    @Override
//    public void sendResourceMail(String[] to, String subject, String content, String rscPath, String rscId) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        FileSystemResource res = new FileSystemResource(new File(rscPath));
//        helper.addInline(rscId, res);
//        mailSender.send(message);
//    }

    @Override
    public void sendResourceMail(String[] to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content, true);
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);
        mailSender.send(message);
    }
}
