package com.NTT.NTTTeam.Command.Event;


import com.NTT.NTTTeam.Command.Data.User;
import com.NTT.NTTTeam.Command.Data.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {
    @Autowired
    private UserRepository userRepository;

    @EventHandler
    public void onCreateUser(UserCreateEventObject event)
    {
        User user = new User();
        BeanUtils.copyProperties(event,user);
        userRepository.save(user);
    }

    @EventHandler
    public void onUpdateUser(UserUpdateEventObject event)
    {
        User user = userRepository.findByUserId(event.getUserId());
        user.setUserName(event.getUserName());
        user.setApple(event.getApple());
        user.setFacebook(event.getFacebook());
        user.setEmailAddress(event.getEmailAddress());
        user.setFirstName(event.getFirstName());
        user.setLastName(event.getLastName());
        user.setPhoneNumber(event.getPhoneNumber());
        userRepository.save(user);
    }
}
