package com.NTTT.PersonalInfoService.Command.Aggregate;



import com.NTTT.PersonalInfoService.Command.Command.CreateWeightTrackCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdateWeightTrackCommandObject;
import com.NTTT.PersonalInfoService.Command.Event.WeightTrackCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.WeightTrackUpdateEventObject;
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
public class WeightTrackAggregate {


    @AggregateIdentifier
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "currentWeight")
    private String currentWeight;

    @Column(name = "pyChId")
    private Integer pyChId;



    public WeightTrackAggregate() {
    }

    @CommandHandler
    public WeightTrackAggregate(CreateWeightTrackCommandObject createWeightTrackCommandObject)
    {
        WeightTrackCreateEventObject event = new WeightTrackCreateEventObject();
        BeanUtils.copyProperties(createWeightTrackCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdateWeightTrackCommandObject updateWeightTrackCommandObject)
    {
        WeightTrackUpdateEventObject userUpdateEventObject = new WeightTrackUpdateEventObject();
        BeanUtils.copyProperties(updateWeightTrackCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }

    @EventSourcingHandler
    public void on(WeightTrackCreateEventObject event)
    {
       this.currentWeight = event.getCurrentWeight();
       this.id = event.getId();
       this.pyChId = event.getPyChId();
       this.recordDate =event.getRecordDate();
    }

    @EventSourcingHandler
    public void on(WeightTrackUpdateEventObject event)
    {
        this.currentWeight = event.getCurrentWeight();
        this.id = event.getId();
        this.pyChId = event.getPyChId();
        this.recordDate =event.getRecordDate();
    }
}
