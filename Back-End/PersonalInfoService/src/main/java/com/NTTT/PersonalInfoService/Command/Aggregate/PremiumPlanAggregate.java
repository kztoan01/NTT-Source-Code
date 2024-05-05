package com.NTTT.PersonalInfoService.Command.Aggregate;



import com.NTTT.PersonalInfoService.Command.Command.CreatePremiumPlanCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdatePremiumPlanCommandObject;
import com.NTTT.PersonalInfoService.Command.Event.PremiumPlanCreateEventObject;
import com.NTTT.PersonalInfoService.Command.Event.PremiumPlanUpdateEventObject;
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
public class PremiumPlanAggregate {


    @AggregateIdentifier
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "premiumType")
    private String premiumType;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;





    public PremiumPlanAggregate() {
    }

    @CommandHandler
    public PremiumPlanAggregate(CreatePremiumPlanCommandObject createPremiumPlanCommandObject)
    {
        PremiumPlanCreateEventObject event = new PremiumPlanCreateEventObject();
        BeanUtils.copyProperties(createPremiumPlanCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdatePremiumPlanCommandObject updatePremiumPlanCommandObject)
    {
        PremiumPlanUpdateEventObject userUpdateEventObject = new PremiumPlanUpdateEventObject();
        BeanUtils.copyProperties(updatePremiumPlanCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }

    @EventSourcingHandler
    public void on(PremiumPlanCreateEventObject event)
    {
        this.endDate = event.getEndDate();
        this.id = event.getId();
        this.premiumType = event.getPremiumType();
        this.userId = event.getUserId();
        this.startDate = event.getStartDate();

    }

    @EventSourcingHandler
    public void on(PremiumPlanUpdateEventObject event)
    {
        this.endDate = event.getEndDate();
        this.id = event.getId();
        this.premiumType = event.getPremiumType();
        this.userId = event.getUserId();
        this.startDate = event.getStartDate();
    }
}
