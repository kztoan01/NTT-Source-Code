package com.NTTT.PersonalInfoService.Command.Data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "PhysicChars")
public class PhysicChars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "physicCharsId")
    private String physicCharsId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "height")
    private String height;

    @Column(name = "weightGoal")
    private String weightGoal;

    @Column(name = "goal")
    private String goal;


    @Column(name = "dietType")
    private String dietType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activityLevelId", referencedColumnName = "id")
    private ActivityLevel activityLevel;

    @OneToMany(mappedBy = "physicChars", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WeightTrack> weightTracks;

}
