package com.NTT.NTTTeam.Command.Aggregate;


import com.NTT.NTTTeam.Command.Command.CreateUserCommandObject;
import com.NTT.NTTTeam.Command.Command.UpdateUserCommandObject;
import com.NTT.NTTTeam.Command.Event.UserCreateEventObject;
import com.NTT.NTTTeam.Command.Event.UserUpdateEventObject;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Aggregate
public class UserAggregate {




    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @AggregateIdentifier
    @Column(name = "userId")
    private String userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber", nullable = false,unique = true)
    private String phoneNumber;



    @Column(name = "emailAddress",unique = true)
    private String emailAddress;

    @Column(name = "userName",nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "apple")
    private String apple;

    @Column(name = "activeStatus")
    private Boolean activeStatus;


    @Column(name = "userRole",nullable = false)
    private Integer userRole;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommandObject createUserCommandObject)
    {
        UserCreateEventObject event = new UserCreateEventObject();
        BeanUtils.copyProperties(createUserCommandObject, event);
        AggregateLifecycle.apply(event);

    }


    @CommandHandler
    public void handleUpdate(UpdateUserCommandObject updateUserCommandObject)
    {
        UserUpdateEventObject userUpdateEventObject = new UserUpdateEventObject();
        BeanUtils.copyProperties(updateUserCommandObject, userUpdateEventObject);
        AggregateLifecycle.apply(userUpdateEventObject);

    }

    @EventSourcingHandler
    public void on(UserCreateEventObject event)
    {
        this.userId = event.getUserId();
        this.apple = event.getApple();
        this.userName = event.getUserName();
        this.activeStatus = event.getActiveStatus();
        this.emailAddress = event.getEmailAddress();
        this.lastName = event.getLastName();
        this.phoneNumber = event.getPhoneNumber();
        this.facebook = event.getFacebook();
        this.userRole = event.getUserRole();
        this.firstName = event.getFirstName();
    }

    @EventSourcingHandler
    public void on(UserUpdateEventObject event)
    {
        this.userId = event.getUserId();
        this.apple = event.getApple();
        this.userName = event.getUserName();
        this.activeStatus = event.getActiveStatus();
        this.emailAddress = event.getEmailAddress();
        this.lastName = event.getLastName();
        this.phoneNumber = event.getPhoneNumber();
        this.facebook = event.getFacebook();
        this.userRole = event.getUserRole();
        this.firstName = event.getFirstName();
    }
}
