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

    private UserPlanning calculateUserPlanning(PhysicChars physicChars) {
        float baseCalories;
        float neededCalories;
        float caloriesBaseOnActivityLevel;
        float neededProtein;
        float neededCarbs;
        float neededFat;
    

        List<WeightTrack> weightTracks = physicChars.getWeightTracks();
        if (weightTracks.isEmpty()) {
            throw new IllegalArgumentException("Weight tracks list is empty");
        }
        WeightTrack lastWeightTrack = weightTracks.get(weightTracks.size() - 1);
        int currentWeight = Integer.parseInt(lastWeightTrack.getCurrentWeight());
        int height = Integer.parseInt(physicChars.getHeight());
    

        if (physicChars.getSex()) {
            baseCalories = (float) ((10 * currentWeight) 
                                  + (6.25 * height) 
                                  - (5 * physicChars.getAge()) + 5);
        } else {
            baseCalories = (float) ((10 * currentWeight) 
                                  + (6.25 * height) 
                                  - (5 * physicChars.getAge()) - 161);
        }
    

        String activityLevel = physicChars.getActivityLevel().getActivityName();
        float activityLevelMultiplier;
        activityLevelMultiplier = switch (activityLevel) {
            case "SEDENTARY" -> 1.2f;
            case "LIGHT" -> 1.375f;
            case "MODERATE" -> 1.465f;
            case "ACTIVE" -> 1.55f;
            case "VERY" -> 1.73f;
            default -> throw new IllegalArgumentException("Invalid activityLevel: " + activityLevel);
        };
        caloriesBaseOnActivityLevel = baseCalories * activityLevelMultiplier;

        String goal = physicChars.getGoal();
        neededCalories = switch (goal) {
            case "LOSS" -> caloriesBaseOnActivityLevel * 0.85f; 
            case "GAIN" -> caloriesBaseOnActivityLevel + 500;
            case "MAINTENANCE" -> caloriesBaseOnActivityLevel;
            default -> throw new IllegalArgumentException("Invalid goal: " + goal);
        };
    

        String dietType = physicChars.getDietType(); 
        float proteinRatio;
        float carbRatio;
        float fatRatio;
    
        switch (dietType) {
            case "Balanced" -> {
                proteinRatio = 0.25f;
                carbRatio = 0.50f;
                fatRatio = 0.25f;
            }
            case "Low Fat" -> {
                proteinRatio = 0.275f;
                carbRatio = 0.525f;
                fatRatio = 0.20f;
            }
            case "Low Carb" -> {
                proteinRatio = 0.30f;
                carbRatio = 0.40f;
                fatRatio = 0.30f;
            }
            case "High Protein" -> {
                proteinRatio = 0.35f;
                carbRatio = 0.425f;
                fatRatio = 0.225f;
            }
            default -> throw new IllegalArgumentException("Invalid diet type: " + dietType);
        }
    
        // Calculate neededProtein, neededCarbs, and neededFat
        neededProtein = (neededCalories * proteinRatio) / 4.1f;
        neededCarbs = (neededCalories * carbRatio) / 3.75f;
        neededFat = (neededCalories * fatRatio) / 8.8f;
    
        // Create and return UserPlanning object
        UserPlanning userPlanning = new UserPlanning();
        userPlanning.setCalories(neededCalories);
        userPlanning.setCarbs(neededCarbs);
        userPlanning.setFat(neededFat);
        userPlanning.setPlaneType(physicChars.getActivityLevel());
        userPlanning.setNeededFat(neededFat);
    
        return userPlanning;
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
//        if(savedPhysicChars.getId()!=null)
//        {
//          userPlanningService.createUserPlanning()
//        }
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
