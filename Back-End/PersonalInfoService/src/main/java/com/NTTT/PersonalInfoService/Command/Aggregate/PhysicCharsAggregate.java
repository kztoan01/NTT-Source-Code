package com.NTTT.PersonalInfoService.Command.Aggregate;



import com.NTTT.PersonalInfoService.Command.Command.CreatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Event.PhysicCharsCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.PhysicCharsUpdateEventObject;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class PhysicCharsAggregate {


    @AggregateIdentifier
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

    @EventSourcingHandler
    public void on(PhysicCharsCreateEventObject event)
    {
        this.id = event.getId();
        this.activityLevelId = event.getActivityLevelId();
        this.age = event.getAge();
        this.goal = event.getGoal();
        this.sex = event.getSex();
        this.height = event.getHeight();
        this.userId = event.getUserId();
        this.weightGoal = event.getWeightGoal();

    }

    @EventSourcingHandler
    public void on(PhysicCharsUpdateEventObject event)
    {
        this.id = event.getId();
        this.activityLevelId = event.getActivityLevelId();
        this.age = event.getAge();
        this.goal = event.getGoal();
        this.sex = event.getSex();
        this.height = event.getHeight();
        this.userId = event.getUserId();
        this.weightGoal = event.getWeightGoal();
    }
}
