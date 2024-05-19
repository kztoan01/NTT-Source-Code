package com.NTTT.PersonalInfoService.Command.Data;


import com.NTTT.PersonalInfoService.Command.Config.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserPlanning")
public class UserPlanning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "userPlanningId")
    private String userPlanningId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "protein")
    private Float protein ;

    @Column(name = "carbs")
    private Float carbs ;

    @Column(name = "fat")
    private Float fat ;

    @Column(name = "calories")
    private Float calories ;

    @Column(name = "planType")
    @Enumerated(EnumType.ORDINAL)
    private Enum.PlanType planType ;

}
