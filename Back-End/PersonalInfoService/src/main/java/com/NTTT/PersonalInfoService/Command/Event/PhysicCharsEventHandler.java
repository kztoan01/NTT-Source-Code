package com.NTTT.PersonalInfoService.Command.Event;


import com.NTTT.PersonalInfoService.Command.Data.PhysicChars;
import com.NTTT.PersonalInfoService.Command.Data.PhysicCharsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhysicCharsEventHandler {
    @Autowired
    private PhysicCharsRepository physicCharsRepository;

    @EventHandler
    public void onCreatePhysicChars(PhysicCharsCreateEventObject event)
    {
        PhysicChars physicChars = new PhysicChars();
        BeanUtils.copyProperties(event,physicChars);
        physicCharsRepository.save(physicChars);
    }

    @EventHandler
    public void onUpdatePhysicChars(PhysicCharsUpdateEventObject event)
    {
        PhysicChars physicChars = physicCharsRepository.findById(event.getId()).orElseThrow();
        physicChars.setUserId(event.getUserId());
        physicChars.setAge(event.getAge());
        physicChars.setSex(event.getSex());
        physicChars.setHeight(event.getHeight());
        physicChars.setWeightGoal(event.getWeightGoal());
        physicChars.setGoal(event.getGoal());
        physicChars.setActivityLevel(event.getActivityLevel());
        physicChars.setWeightTracks(event.getWeightTracks());
        physicCharsRepository.save(physicChars);
    }
}
