package com.zlhj.pm.service;

import javax.mail.MessagingException;

/**
 * 邮件发送
 *
 * @author tzm
 */
public interface IMailService {
    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件正文
     */
//    void sendSimpleMail(String[] to, String subject, String content);

    void sendSimpleMail(String[] to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件正文
     * @throws MessagingException
     */
//    void sendHtmlMail(String[] to, String subject, String content) throws MessagingException;

    void sendHtmlMail(String[] to, String subject, String content, String... cc) throws MessagingException;

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  邮件主题
     * @param content  邮件正文
     * @param filePath 附件路径
     * @throws MessagingException MessagingException
     */
//    void sendAttachmentsMail(String[] to, String subject, String content, String filePath) throws MessagingException;

    void sendAttachmentsMail(String[] to, String subject, String content, String filePath, String... cc) throws MessagingException;

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件正文
     * @param rscPath 附件路径
     * @param rscId   内联图像
     * @throws MessagingException MessagingException
     */
//    void sendResourceMail(String[] to, String subject, String content, String rscPath, String rscId) throws MessagingException;

    void sendResourceMail(String[] to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException;

}