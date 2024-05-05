//package com.NTTT.EmailService.Controller;
//
//
//import com.NTTT.EmailService.Service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/mail")
//public class MailController {
//
//    @Autowired
//    MailService mailService;
//    @PostMapping("/sendMail/{mail}")
//    public String sendMail( @RequestParam String Subject, @RequestParam String Message, @PathVariable String mail)
//    {
//       mailService.sendMail(Subject,Message,mail);
//       return "Sent!";
//    }
//}
