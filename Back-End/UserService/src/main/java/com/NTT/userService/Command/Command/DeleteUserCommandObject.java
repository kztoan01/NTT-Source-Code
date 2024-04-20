package com.NTT.userService.Command.Command;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteUserCommandObject {


    @TargetAggregateIdentifier
    @Column(name = "userId")
    private String userId;

    public DeleteUserCommandObject(String userId) {
        this.userId = userId;
    }
}
