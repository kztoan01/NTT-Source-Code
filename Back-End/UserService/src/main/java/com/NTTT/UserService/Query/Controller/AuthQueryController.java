package com.NTTT.UserService.Query.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NTTT.UserService.Command.Service.AuthCommandService;
import com.NTTT.UserService.Query.Service.AuthQueryService;



@RestController
@RequestMapping("/auth")
public class AuthQueryController {

    @Autowired
    private AuthCommandService authCommandService;


    @Autowired
    private AuthQueryService authQueryService;

    Logger logger
            = LoggerFactory.getLogger(AuthQueryController.class);


}
