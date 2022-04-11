package cn.race.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

//    private String from="text/html";

    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        //创建MIME样式的电子邮件对象
        MimeMessage message = mailSender.createMimeMessage();
         message.setFrom("907167912@qq.com");
        //用于填充MimeMessage的帮助类
        //参数1填入MimeMessage对象
        //参数2表示是否创建支持替代文本、内联元素和附件的多部分消息
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        //参数2表示是否使用默认内容类型为HTML邮件应用内容类型"text/html"
        helper.setText(content, true);
        mailSender.send(message);
    }
}

