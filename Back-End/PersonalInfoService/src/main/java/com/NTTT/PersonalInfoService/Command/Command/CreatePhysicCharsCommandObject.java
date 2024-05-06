package com.NTTT.PersonalInfoService.Command.Command;

import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.List;

@Data
public class CreatePhysicCharsCommandObject {


    private Integer id;



    private String userId;


    @TargetAggregateIdentifier
    private String physicCharsId;

    private Integer age;

    private Boolean sex;

    private String height;

    private String weightGoal;

    private String goal;

    private ActivityLevel activityLevel;

    private List<WeightTrack> weightTracks;


    public CreatePhysicCharsCommandObject(String userId, String physicCharsId, Integer age, Boolean sex, String height, String weightGoal, String goal, ActivityLevel activityLevel, List<WeightTrack> weightTracks) {
        this.userId = userId;
        this.physicCharsId = physicCharsId;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weightGoal = weightGoal;
        this.goal = goal;
        this.activityLevel = activityLevel;
        this.weightTracks = weightTracks;
    }
}
