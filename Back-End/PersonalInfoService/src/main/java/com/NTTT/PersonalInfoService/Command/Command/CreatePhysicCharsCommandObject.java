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
@AllArgsConstructor
public class CreatePhysicCharsCommandObject {


    private String userId;


    @TargetAggregateIdentifier
    private String physicCharsId;

    private Integer age;

    private Boolean sex;

    private String height;

    private String weightGoal;

    private String goal;

    private String dietType;

    private ActivityLevel activityLevel;

    private List<WeightTrack> weightTracks;



}
