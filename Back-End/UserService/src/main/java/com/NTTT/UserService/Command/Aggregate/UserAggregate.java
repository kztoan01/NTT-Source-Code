package com.NTTT.UserService.Command.Aggregate;


import com.NTTT.UserService.Command.Controller.AuthCommandController;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import com.NTTT.UserService.Command.Command.CreateUserCommandObject;
import com.NTTT.UserService.Command.Command.UpdateUserCommandObject;
import com.NTTT.UserService.Command.Event.UserCreateEventObject;
import com.NTTT.UserService.Command.Event.UserUpdateEventObject;

@Aggregate
public class UserAggregate {

    Logger logger
            = LoggerFactory.getLogger(AuthCommandController.class);

    @AggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String userName;

    private String password;

    private String facebook;

    private String apple;

    private Boolean activeStatus;


    private Integer userRole;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommandObject createUserCommandObject)
    {
        logger.info("test1");
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
        logger.info("Test1.5");
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
        this.lastName = event.getLastName();
        this.phoneNumber = event.getPhoneNumber();
        this.facebook = event.getFacebook();
        this.userRole = event.getUserRole();
        this.firstName = event.getFirstName();
    }
}
