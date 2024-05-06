package com.NTTT.EmailService.Service;


import com.NTTT.EmailService.Model.EmailObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger
            = LoggerFactory.getLogger(MailService.class);


    @Value("NTTT")
    private String fromMail;

    @KafkaListener(id = "notification",topics = "notification")
    public void sendMail(EmailObject emailObject)
    {
        logger.info("Catched:"+emailObject.getRecipient());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("Register verifying");
        simpleMailMessage.setText(emailObject.getMessage()+emailObject.getOtp());
        simpleMailMessage.setTo(emailObject.getRecipient());
        javaMailSender.send(simpleMailMessage);
    }
}
