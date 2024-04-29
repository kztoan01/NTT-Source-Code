package com.NTTT.UserService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EMAILSERVICE",path = "/mail")
public interface EmailClient {
    @PostMapping("/sendMail/{mail}")
    @Async
    public String sendMail( @RequestParam String Subject, @RequestParam String Message, @PathVariable String mail);

}
