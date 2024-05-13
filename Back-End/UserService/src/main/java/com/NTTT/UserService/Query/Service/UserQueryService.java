package com.NTTT.UserService.Query.Service;

import com.NTTT.UserService.Command.Controller.AuthCommandController;
import com.NTTT.UserService.Command.Data.UserRepository;
import com.NTTT.UserService.Command.Model.ResponseObject;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.NTTT.UserService.Query.Model.ResponseUserDTO;
import com.NTTT.UserService.Query.Queries.GetAllUsersQuery;
import com.NTTT.UserService.Query.Queries.GetUserQuery;

import java.util.List;


@Service
public class UserQueryService {

    @Autowired
    private QueryGateway queryGateway;

    Logger logger
            = LoggerFactory.getLogger(UserQueryService.class);

    @Autowired
    UserRepository userRepository;

    public List<ResponseUserDTO> getAllUser(){
        GetAllUsersQuery getAllUsersQuery = new GetAllUsersQuery();
        List<ResponseUserDTO> list = queryGateway.query(getAllUsersQuery, ResponseTypes.multipleInstancesOf(ResponseUserDTO.class))
                .join();
        return list;
    }
    
    public ResponseObject getUserDetail(String UserId) {
        GetUserQuery getUsersQuery = new GetUserQuery();
        ResponseObject responseObject = new ResponseObject();
        try
        {
            getUsersQuery.setUserId(UserId);
            ResponseUserDTO userResponseModel =
                    queryGateway.query(getUsersQuery,
                                    ResponseTypes.instanceOf(ResponseUserDTO.class))
                            .join();
            responseObject.setStatusCode(200);
            responseObject.setResponseUserDTO(userResponseModel);
            responseObject.setChangeSuccessfully(false);
        }
        catch (Exception e)
        {
            responseObject.setStatusCode(404);
            responseObject.setMessage("Error Query User.Not Found!");
            responseObject.setChangeSuccessfully(false);
        }


        return responseObject;
    }

    public ResponseObject getUserDetailByUsername(String username) {
        GetUserQuery getUsersQuery = new GetUserQuery();

        ResponseObject responseObject = new ResponseObject();


        try
        {
            logger.info(userRepository.findByUserName(username).orElseThrow().getUserId());
            getUsersQuery.setUserId(userRepository.findByUserName(username).orElseThrow().getUserId());
            ResponseUserDTO userResponseModel =
                    queryGateway.query(getUsersQuery,
                                    ResponseTypes.instanceOf(ResponseUserDTO.class))
                            .join();
            responseObject.setStatusCode(200);
            responseObject.setResponseUserDTO(userResponseModel);
            responseObject.setChangeSuccessfully(false);
        }
        catch (Exception e)
        {
            responseObject.setStatusCode(404);
            responseObject.setMessage("Error Query User.Not Found!");
            responseObject.setChangeSuccessfully(false);
        }
        return responseObject;
    }

    public ResponseObject getUserDetailByUserEmail(String userEmail) {
        GetUserQuery getUsersQuery = new GetUserQuery();
        ResponseObject responseObject = new ResponseObject();
        logger.info("Test"+userRepository.findByEmailAddress(userEmail).orElseThrow().getUserId());
        try
        {
            getUsersQuery.setUserId(userRepository.findByEmailAddress(userEmail).orElseThrow().getUserId());

            ResponseUserDTO userResponseModel =
                    queryGateway.query(getUsersQuery,
                                    ResponseTypes.instanceOf(ResponseUserDTO.class))
                            .join();
            responseObject.setStatusCode(200);
            responseObject.setResponseUserDTO(userResponseModel);
            responseObject.setChangeSuccessfully(false);
        }
        catch (Exception e)
        {
            logger.info(e.toString());
            responseObject.setStatusCode(404);
            responseObject.setMessage("User email not found!");
            responseObject.setChangeSuccessfully(false);
        }
        return responseObject;
    }
}
