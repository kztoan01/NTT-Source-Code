package com.NTTT.PersonalInfoService.Command.Event;

import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class PhysicCharsCreateEventObject {

    private Integer id;

    private String userId;

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
