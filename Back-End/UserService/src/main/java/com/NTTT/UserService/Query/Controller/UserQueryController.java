package com.NTTT.UserService.Query.Controller;


import com.NTTT.UserService.Command.Model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NTTT.UserService.Command.Data.User;
import  com.NTTT.UserService.Query.Model.ResponseUserDTO;
import  com.NTTT.UserService.Query.Queries.GetUserQuery;
import  com.NTTT.UserService.Query.Service.UserQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserQueryController {



    @Autowired
    UserQueryService userQueryService;

    @GetMapping
    List<ResponseUserDTO> getAllUser()
    {
           return userQueryService.getAllUser();
    }

    @GetMapping("/getByID/{userId}")
    public ResponseObject getUserDetailByUserID(@PathVariable String userId) {

        return userQueryService.getUserDetail(userId);

    }

    @GetMapping("/getByName/{userName}")
    public ResponseObject getUserDetailByUsername(@PathVariable String userName) {

        return userQueryService.getUserDetailByUsername(userName);

    }
}
