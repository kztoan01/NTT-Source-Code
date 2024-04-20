package com.NTTT.UserService.Command.Command;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateUserCommandObject {

    @TargetAggregateIdentifier
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


    @Column(name = "facebook")
    private String facebook;

    @Column(name = "apple")
    private String apple;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }


    public UpdateUserCommandObject(String userId, String firstName, String lastName, String phoneNumber, String emailAddress, String userName, String facebook, String apple) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.facebook = facebook;
        this.apple = apple;
    }
}
