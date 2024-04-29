package com.NTTT.UserService.Query.Projection;


import com.NTTT.UserService.Command.Model.ResponseObject;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.NTTT.UserService.Command.Data.User;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Query.Model.ResponseUserDTO;
import com.NTTT.UserService.Query.Queries.GetAllUsersQuery;
import com.NTTT.UserService.Query.Queries.GetUserQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProjection {
    @Autowired
    private UserRepository UserRepository;

    @QueryHandler
    public ResponseObject handle(GetUserQuery getUsersQuery) {


        ResponseObject responseObject = new ResponseObject();
        ResponseUserDTO model = new ResponseUserDTO();
        try
        {
            User user = UserRepository.findByUserId(getUsersQuery.getUserId()).orElseThrow();
            BeanUtils.copyProperties(user, model);
            responseObject.setStatusCode(200);
            responseObject.setResponseUserDTO(model);
            responseObject.setChangeSuccessfully(false);
        }
        catch (Exception e)
        {
            responseObject.setStatusCode(404);
            responseObject.setMessage("UserId not found!");
            responseObject.setChangeSuccessfully(false);
        }

        return  responseObject;
    }
    @QueryHandler
    List<ResponseUserDTO> handle(GetAllUsersQuery getAllUsersQuery){
        List<User> listEntity = UserRepository.findAll();
        List<ResponseUserDTO> listUser = new ArrayList<>();
        listEntity.forEach(s -> {
            ResponseUserDTO model = new ResponseUserDTO();
            BeanUtils.copyProperties(s, model);
            listUser.add(model);
        });
        return listUser;
    }

//    @QueryHandler
//    public UserResponseCommonModel handle(GetDetailsUserQuery getDetailsUserQuery) {
//        UserResponseCommonModel model = new UserResponseCommonModel();
//        User User = UserRepository.getById(getDetailsUserQuery.getUserId());
//        BeanUtils.copyProperties(User, model);
//
//        return model;
//    }
}
