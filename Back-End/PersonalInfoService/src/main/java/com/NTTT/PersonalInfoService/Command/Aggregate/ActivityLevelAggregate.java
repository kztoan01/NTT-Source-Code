package com.NTTT.PersonalInfoService.Command.Aggregate;



import com.NTTT.PersonalInfoService.Command.Command.CreateActivityLevelCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdateActivityLevelCommandObject;
import com.NTTT.PersonalInfoService.Command.Event.ActivityLevelCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.ActivityLevelUpdateEventObject;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ActivityLevelAggregate {


    @AggregateIdentifier
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "ActivityName")
    private String ActivityName;

    @Column(name = "ActivityType")
    private Integer ActivityType;



    public ActivityLevelAggregate() {
    }

    @CommandHandler
    public ActivityLevelAggregate(CreateActivityLevelCommandObject createActivityLevelCommandObject)
    {
        ActivityLevelCreateEventObject event = new ActivityLevelCreateEventObject();
        BeanUtils.copyProperties(createActivityLevelCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdateActivityLevelCommandObject updateActivityLevelCommandObject)
    {
        ActivityLevelUpdateEventObject userUpdateEventObject = new ActivityLevelUpdateEventObject();
        BeanUtils.copyProperties(updateActivityLevelCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }

    @EventSourcingHandler
    public void on(ActivityLevelCreateEventObject event)
    {
        this.id = event.getId();
        this.ActivityName = event.getActivityName();
        this.ActivityType = event.getActivityType();
    }

    @EventSourcingHandler
    public void on(ActivityLevelUpdateEventObject event)
    {
        this.id = event.getId();
        this.ActivityName = event.getActivityName();
        this.ActivityType = event.getActivityType();
    }
}
