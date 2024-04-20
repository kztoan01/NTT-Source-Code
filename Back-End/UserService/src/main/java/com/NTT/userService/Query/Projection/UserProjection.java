package com.NTT.userService.Query.Projection;


import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.NTT.userService.Command.Data.User;
import com.NTT.userService.Command.Data.UserRepository;
import com.NTT.userService.Query.Model.ResponseUserDTO;
import com.NTT.userService.Query.Queries.GetAllUsersQuery;
import com.NTT.userService.Query.Queries.GetUserQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProjection {
    @Autowired
    private UserRepository UserRepository;

    @QueryHandler
    public ResponseUserDTO handle(GetUserQuery getUsersQuery) {
        ResponseUserDTO model = new ResponseUserDTO();
        User user = UserRepository.findByUserId(getUsersQuery.getUserId());
        BeanUtils.copyProperties(user, model);

        return model;
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
