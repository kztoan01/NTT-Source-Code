package com.NTTT.PersonalInfoService.Command.Event;

import com.NTTT.PersonalInfoService.Command.Config.Enum;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UserPlanningCreateEventObject {

    private Integer id;
    private String userPlanningId;

    private Float protein ;

    private Float carbs ;

    private Float fat ;

    private Float calories ;

    private Enum.PlaneType planeType ;

}
