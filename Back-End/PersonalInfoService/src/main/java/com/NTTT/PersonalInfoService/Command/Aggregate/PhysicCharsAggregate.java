package com.NTTT.PersonalInfoService.Command.Aggregate;



import com.NTTT.PersonalInfoService.Command.Command.CreatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdatePhysicCharsWeightCommandObject;
import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import com.NTTT.PersonalInfoService.Command.Event.PhysicCharsCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.PhysicCharsUpdateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.PhysicCharsUpdateWeightTrackEventObject;
import com.NTTT.PersonalInfoService.Command.Model.ChangePersonalInfoRequestDTO;
import jakarta.persistence.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Aggregate
public class PhysicCharsAggregate {

    Logger logger
            = LoggerFactory.getLogger(PhysicCharsAggregate.class);


    private Integer id;


    private String userId;


    @AggregateIdentifier
    private String physicCharsId;

    private Integer age;

    private Boolean sex;

    private String height;

    private String weightGoal;

    private String goal;

    private String dietType;

    private ActivityLevel activityLevel;

    private List<WeightTrack> weightTracks;









    public PhysicCharsAggregate() {
    }

    @CommandHandler
    public PhysicCharsAggregate(CreatePhysicCharsCommandObject createPhysicCharsCommandObject)
    {
        PhysicCharsCreateEventObject event = new PhysicCharsCreateEventObject();
        BeanUtils.copyProperties(createPhysicCharsCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdatePhysicCharsCommandObject updatePhysicCharsCommandObject)
    {
        PhysicCharsUpdateEventObject userUpdateEventObject = new PhysicCharsUpdateEventObject();
        BeanUtils.copyProperties(updatePhysicCharsCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }

    @CommandHandler
    public void handleUpdateWeightTrack(UpdatePhysicCharsWeightCommandObject updatePhysicCharsWeightCommandObject)
    {
        logger.info("Test1");
        PhysicCharsUpdateWeightTrackEventObject physicCharsUpdateWeightTrackEventObject = new PhysicCharsUpdateWeightTrackEventObject();
        BeanUtils.copyProperties(updatePhysicCharsWeightCommandObject, physicCharsUpdateWeightTrackEventObject);
        AggregateLifecycle.apply(physicCharsUpdateWeightTrackEventObject);

    }

    @EventSourcingHandler
    public void on(PhysicCharsCreateEventObject event)
    {
        this.id = event.getId();
        this.weightGoal = event.getWeightGoal();
        this.physicCharsId =event.getPhysicCharsId();
        this.activityLevel = event.getActivityLevel();
        this.age = event.getAge();
        this.goal = event.getGoal();
        this.sex = event.getSex();
        this.height = event.getHeight();
        this.userId = event.getUserId();
        this.dietType = event.getDietType();
    }

    @EventSourcingHandler
    public void on(PhysicCharsUpdateEventObject event)
    {
        this.id = event.getId();
        this.weightGoal = event.getWeightGoal();
        this.physicCharsId = event.getPhysicCharsId();
        this.activityLevel = event.getActivityLevel();
        this.age = event.getAge();
        this.goal = event.getGoal();
        this.sex = event.getSex();
        this.height = event.getHeight();
        this.userId = event.getUserId();
        this.dietType = event.getDietType();
    }

    @EventSourcingHandler
    public void on(PhysicCharsUpdateWeightTrackEventObject event)
    {
           logger.info("Test2");
           this.physicCharsId = event.getPhysicCharsId();
           this.weightTracks = event.getWeightTracks();
    }
}
