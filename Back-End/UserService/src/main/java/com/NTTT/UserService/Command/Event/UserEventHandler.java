package com.NTTT.UserService.Command.Event;


import com.NTTT.UserService.Command.Controller.AuthCommandController;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.NTTT.UserService.Command.Data.User;
import com.NTTT.UserService.Command.Data.UserRepository;

@Component
public class UserEventHandler {
    @Autowired
    private UserRepository userRepository;
    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);


    @EventHandler
    public void onCreateUser(UserCreateEventObject event)
    {
        logger.info("test2");
        User user = new User();
        BeanUtils.copyProperties(event,user);
        userRepository.save(user);
    }

    @EventHandler
    public void onUpdateUser(UserUpdateEventObject event)
    {
        User user = userRepository.findByUserId(event.getUserId()).orElseThrow();
        user.setUserName(event.getUserName());
        user.setApple(event.getApple());
        user.setFacebook(event.getFacebook());
        user.setFirstName(event.getFirstName());
        user.setLastName(event.getLastName());
        user.setPhoneNumber(event.getPhoneNumber());
        userRepository.save(user);
    }
}
