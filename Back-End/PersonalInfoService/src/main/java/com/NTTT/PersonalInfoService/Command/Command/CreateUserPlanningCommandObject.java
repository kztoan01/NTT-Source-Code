package com.NTTT.PersonalInfoService.Command.Command;


import com.NTTT.PersonalInfoService.Command.Config.Enum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserPlanningCommandObject {

    @TargetAggregateIdentifier
    private String userPlanningId;

    private Float protein ;

    private Float carbs ;

    private Float fat ;

    private Float calories ;

    private Enum.PlanType planType ;

}
