package com.NTTT.PersonalInfoService.Command.Aggregate;

import com.NTTT.PersonalInfoService.Command.Command.CreateUserPlanningCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdateUserPlanningCommandObject;
import com.NTTT.PersonalInfoService.Command.Config.Enum;
import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import com.NTTT.PersonalInfoService.Command.Event.UserPlanningCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.UserPlanningUpdateEventObject;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UserPlanningAggregate {

    Logger logger
            = LoggerFactory.getLogger(UserPlanningAggregate.class);
    

    private Integer id;


    @AggregateIdentifier
    private String userPlanningId;
    
    private Float protein ;
    
    private Float carbs ;
    
    private Float fat ;

    private Float calories ;

    private Enum.PlaneType planeType ;




    public UserPlanningAggregate() {
    }

    @CommandHandler
    public UserPlanningAggregate(CreateUserPlanningCommandObject createUserPlanningCommandObject)
    {
        UserPlanningCreateEventObject event = new UserPlanningCreateEventObject();
        BeanUtils.copyProperties(createUserPlanningCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdateUserPlanningCommandObject updateUserPlanningCommandObject)
    {
        UserPlanningUpdateEventObject userUpdateEventObject = new UserPlanningUpdateEventObject();
        BeanUtils.copyProperties(updateUserPlanningCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }


    @EventSourcingHandler
    public void on(UserPlanningCreateEventObject event)
    {
        this.id = event.getId();
        this.userPlanningId = event.getUserPlanningId();
        this.fat = event.getFat();
        this.protein = event.getProtein();
        this.carbs = event.getCarbs();
    }

    @EventSourcingHandler
    public void on(UserPlanningUpdateEventObject event)
    {
        this.id = event.getId();
        this.userPlanningId = event.getUserPlanningId();
        this.fat = event.getFat();
        this.protein = event.getProtein();
        this.carbs = event.getCarbs();
    }

}
