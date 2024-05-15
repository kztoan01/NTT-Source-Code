package com.NTTT.PersonalInfoService.Command.Event;


import com.NTTT.PersonalInfoService.Command.Data.UserPlanning;
import com.NTTT.PersonalInfoService.Command.Data.UserPlanningRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPlanningEventHandler {

    @Autowired
    private UserPlanningRepository userPlanningRepository;

    Logger logger
            = LoggerFactory.getLogger(UserPlanningEventHandler.class);

    @EventHandler
    public void onCreateUserPlanning(UserPlanningCreateEventObject event)
    {
        UserPlanning userPlanning = new UserPlanning();
        BeanUtils.copyProperties(event,userPlanning);
        userPlanningRepository.save(userPlanning);
    }


    @EventHandler
    public void onUpdateUserPlanning(UserPlanningUpdateEventObject event)
    {
        UserPlanning userPlanning = userPlanningRepository.findById(event.getId()).orElseThrow();
        userPlanningRepository.save(userPlanning);
    }

}
