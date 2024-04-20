package com.NTT.userService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EMAILSERVICE",path = "/mail")
public interface EmailClient {
    @PostMapping("/sendMail/{mail}")
    public String sendMail( @RequestParam String Subject, @RequestParam String Message, @PathVariable String mail);

}
