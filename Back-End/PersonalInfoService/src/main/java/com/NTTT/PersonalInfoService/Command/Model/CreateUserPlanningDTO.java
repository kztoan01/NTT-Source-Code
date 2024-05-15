package com.NTTT.PersonalInfoService.Command.Model;

import com.NTTT.PersonalInfoService.Command.Config.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserPlanningDTO {


    private String userPlanningId;

    private Float protein ;

    private Float carbs ;

    private Float fat ;

    private Float calories ;

    @Enumerated(EnumType.ORDINAL)
    private Enum.PlaneType planeType ;
}
