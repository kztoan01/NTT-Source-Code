package com.NTT.NTTTeam.Command.Command;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class CreateUserCommandObject {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public CreateUserCommandObject(String userId, String firstName, String lastName, String phoneNumber, String emailAddress, String userName, String password, String facebook, String apple, Boolean activeStatus, Integer userRole) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.password = password;
        this.facebook = facebook;
        this.apple = apple;
        this.activeStatus = activeStatus;
        this.userRole = userRole;
    }
}
