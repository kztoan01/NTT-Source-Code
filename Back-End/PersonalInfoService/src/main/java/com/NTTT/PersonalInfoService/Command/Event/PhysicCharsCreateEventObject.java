package com.NTTT.PersonalInfoService.Command.Event;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;


@Data
public class PhysicCharsCreateEventObject {
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "age")
    private Date age;

    @Column(name = "sex")
    private Date sex;

    @Column(name = "height")
    private String height;

    @Column(name = "weightGoal")
    private String weightGoal;

    @Column(name = "goal")
    private String goal;

    @Column(name = "activityLevelId")
    private Integer activityLevelId;
}
