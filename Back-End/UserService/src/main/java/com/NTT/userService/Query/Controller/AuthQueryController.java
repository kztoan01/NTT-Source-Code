package com.NTT.userService.Query.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NTT.userService.Command.Model.AuthenticationRequest;
import com.NTT.userService.Command.Model.RegisterRequest;
import com.NTT.userService.Command.Model.ResponseObject;
import com.NTT.userService.Command.service.AuthCommandService;
import com.NTT.userService.Query.Service.AuthQueryService;



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
