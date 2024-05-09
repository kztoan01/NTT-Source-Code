package com.NTTT.PersonalInfoService.Command.Event;


import com.NTTT.PersonalInfoService.Command.Data.PhysicChars;
import com.NTTT.PersonalInfoService.Command.Data.PhysicCharsRepository;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhysicCharsEventHandler {
    @Autowired
    private PhysicCharsRepository physicCharsRepository;

    Logger logger
            = LoggerFactory.getLogger(PhysicCharsEventHandler.class);

    @EventHandler
    public void onCreatePhysicChars(PhysicCharsCreateEventObject event)
    {
        PhysicChars physicChars = new PhysicChars();
        BeanUtils.copyProperties(event,physicChars);
        PhysicChars savedPhysicChars = physicCharsRepository.save(physicChars);
        event.getWeightTracks().forEach(weightTrack -> weightTrack.setPhysicChars(savedPhysicChars));
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

    @EventHandler
    public void onUpdatePhysicCharsWeightTrack(PhysicCharsUpdateWeightTrackEventObject event)
    {
        PhysicChars physicChars = physicCharsRepository.findByPhysicCharsId(event.getPhysicCharsId()).orElseThrow();
        List<WeightTrack> weightTracks = new ArrayList<>();
        for(WeightTrack weightTrack : event.getWeightTracks())
        {
            weightTrack.setPhysicChars(physicChars);
            weightTracks.add(weightTrack);
        }
        physicChars.setWeightTracks(weightTracks);
        physicCharsRepository.save(physicChars);
    }
}
