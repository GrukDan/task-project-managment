package com.bsuir.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    public JavaMailSender emailSender;

    public String sendEmail(String userName,
                            String userSurname,
                            String recipientMail,
                            String login,
                            String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "Unicode");
        String htmlMsg =
                 "<h2>You are registered in the project and task management system!</h2>"
                        + "<h4>Your login: " + login + "</h4>"
                        + "<h4>Your password: " + password + "</h4>"
                        + "<h4>Regards, Administrator.</h4>"
                        + "<img src='https://www.projectmanager.com/wp-content/themes/projectmanager-bones-2015/library/images/PM-Logo_Blue_Green.png'>";

        message.setContent(htmlMsg, "text/html");
        helper.setTo(recipientMail);
        helper.setSubject("You are registered in the project and task management system!");
        this.emailSender.send(message);
        return "Email Sent!";
    }
}
