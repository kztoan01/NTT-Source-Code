package com.NTT.NTTTeam.Query.Projection;


import com.NTT.NTTTeam.Command.Data.User;
import com.NTT.NTTTeam.Command.Data.UserRepository;
import com.NTT.NTTTeam.Query.Model.ResponseUserDTO;
import com.NTT.NTTTeam.Query.Queries.GetAllUsersQuery;
import com.NTT.NTTTeam.Query.Queries.GetUserQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
