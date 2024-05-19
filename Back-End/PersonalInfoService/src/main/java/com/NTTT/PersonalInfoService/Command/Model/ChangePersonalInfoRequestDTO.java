package com.NTTT.PersonalInfoService.Command.Model;

import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ChangePersonalInfoRequestDTO {


    private String userId;

    private Integer age;

    private Boolean sex;

    private String height;

    private String weightGoal;

    private String goal;

    private String dietType;

    private ActivityLevel activityLevel;

    private List<WeightTrack> weightTracks;

}
