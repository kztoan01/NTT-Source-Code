package com.NTTT.EmailService.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    private String fromMail;

    public void sendMail(String Subject,String Message,String Recipient)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(Subject);
        simpleMailMessage.setText(Message);
        simpleMailMessage.setTo(Recipient);
        javaMailSender.send(simpleMailMessage);
    }
}
