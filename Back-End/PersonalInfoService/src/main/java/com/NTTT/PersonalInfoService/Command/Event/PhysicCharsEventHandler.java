package com.NTTT.PersonalInfoService.Command.Event;


import com.NTTT.PersonalInfoService.Command.Data.PhysicChars;
import com.NTTT.PersonalInfoService.Command.Data.PhysicCharsRepository;
import com.NTTT.PersonalInfoService.Command.Data.UserPlanning;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import com.NTTT.PersonalInfoService.Command.Service.UserPlanningService;
import com.google.protobuf.UnknownFieldSet;
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

    @Autowired
    private UserPlanningService userPlanningService;


    private UserPlanning caculateUserPlanning(PhysicChars physicChars)
    {
            float baseCalories;
            float neededCalories;
            float caloriesBaseOnActivityLevel;
            float neededProtein;
            float neededCarbs;
            float neededFat;
            float spRemainingEnergy;
        if(physicChars.getSex())
            {
                baseCalories = (float) ((10 * Integer.parseInt(physicChars.getWeightTracks().getLast().getCurrentWeight())) + (6.25 * Integer.parseInt(physicChars.getHeight())) - (5 * physicChars.getAge()) + 5);
            }
            else
            {
                baseCalories = (float) ((10 * Integer.parseInt(physicChars.getWeightTracks().getLast().getCurrentWeight())) + (6.25 * Integer.parseInt(physicChars.getHeight())) - (5 * physicChars.getAge()) - 161);
            }


        String activityLevel = physicChars.getActivityLevel().getActivityName();
        float activityLevelMultiplier;
        switch (activityLevel) {
            case "SEDENTARY":
                activityLevelMultiplier = 1.2f;
                break;
            case "LIGHT":
                activityLevelMultiplier = 1.55f;
                break;
            case "MODERATE":
                activityLevelMultiplier = 1.85f;
                break;
            case "ACTIVE":
                activityLevelMultiplier = 2.2f;
                break;
            case "VERY":
                activityLevelMultiplier = 2.6f;
                break;
            default:
                throw new IllegalArgumentException("Invalid activityLevelMultiplier: " + activityLevelMultiplier);
        }
        caloriesBaseOnActivityLevel = baseCalories * activityLevelMultiplier;

        String goal = physicChars.getGoal();
        neededCalories = switch (goal) {
            case "LOSS" -> caloriesBaseOnActivityLevel * 15 / 100;
            case "GAIN" -> caloriesBaseOnActivityLevel + 500;
            case "MAINTENANCE" -> caloriesBaseOnActivityLevel;
            default -> throw new IllegalArgumentException("Invalid goal: " + goal);
        };

        float proteinMultiplier;
        switch (activityLevelMultiplier) {
            case "SEDENTARY":
                proteinMultiplier = 0.8f;
                break;
            case "LIGHT":
                proteinMultiplier = 1.0f;
                break;
            case "MODERATE":
                proteinMultiplier = 1.3f;
                break;
            case "ACTIVE":
                proteinMultiplier = 1.6f;
                break;
            default:
                throw new IllegalArgumentException("Invalid goal: " + goal);
        }

        neededProtein = Integer.parseInt(physicChars.getWeightTracks().getLast().getCurrentWeight()) * proteinMultiplier;


        spRemainingEnergy = (float)(neededCalories - 4.1 * neededProtein);

        neededCarbs = spRemainingEnergy*currentCarbVal/(currentCarbVal*3.75 + currentFatVal*8.8);







    }


    Logger logger
            = LoggerFactory.getLogger(PhysicCharsEventHandler.class);

    @EventHandler
    public void onCreatePhysicChars(PhysicCharsCreateEventObject event)
    {
        PhysicChars physicChars = new PhysicChars();
        BeanUtils.copyProperties(event,physicChars);
        PhysicChars savedPhysicChars = physicCharsRepository.save(physicChars);
        event.getWeightTracks().forEach(weightTrack -> weightTrack.setPhysicChars(savedPhysicChars));
        if(savedPhysicChars.getId()!=null)
        {
          userPlanningService.createUserPlanning()
        }
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
