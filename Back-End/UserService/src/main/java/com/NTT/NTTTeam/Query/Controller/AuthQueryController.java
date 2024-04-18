package com.NTT.NTTTeam.Query.Controller;

import com.NTT.NTTTeam.Command.Model.AuthenticationRequest;
import com.NTT.NTTTeam.Command.Model.RegisterRequest;
import com.NTT.NTTTeam.Command.Model.ResponseObject;
import com.NTT.NTTTeam.Command.service.AuthCommandService;
import com.NTT.NTTTeam.Query.Service.AuthQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
