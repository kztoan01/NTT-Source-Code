package com.NTTT.PersonalInfoService.Command.Data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PhysicChars")
public class PhysicChars {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
