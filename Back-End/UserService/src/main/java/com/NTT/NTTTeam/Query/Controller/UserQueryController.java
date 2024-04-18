package com.NTT.NTTTeam.Query.Controller;


import com.NTT.NTTTeam.Command.Data.User;
import com.NTT.NTTTeam.Query.Model.ResponseUserDTO;
import com.NTT.NTTTeam.Query.Queries.GetUserQuery;
import com.NTT.NTTTeam.Query.Service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{userId}")
    public ResponseUserDTO getUserDetail(@PathVariable String userId) {

        return userQueryService.getUserDetail(userId);

    }
}
