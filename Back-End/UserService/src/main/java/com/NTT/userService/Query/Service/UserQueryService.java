package com.NTT.userService.Query.Service;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.NTT.userService.Query.Model.ResponseUserDTO;
import com.NTT.userService.Query.Queries.GetAllUsersQuery;
import com.NTT.userService.Query.Queries.GetUserQuery;

import java.util.List;


@Service
public class UserQueryService {

    @Autowired
    private QueryGateway queryGateway;

    public List<ResponseUserDTO> getAllUser(){
        GetAllUsersQuery getAllUsersQuery = new GetAllUsersQuery();
        List<ResponseUserDTO> list = queryGateway.query(getAllUsersQuery, ResponseTypes.multipleInstancesOf(ResponseUserDTO.class))
                .join();
        return list;
    }
    
    public ResponseUserDTO getUserDetail(String UserId) {
        GetUserQuery getUsersQuery = new GetUserQuery();
        getUsersQuery.setUserId(UserId);

        ResponseUserDTO UserResponseModel =
                queryGateway.query(getUsersQuery,
                                ResponseTypes.instanceOf(ResponseUserDTO.class))
                        .join();

        return UserResponseModel;
    }
}
