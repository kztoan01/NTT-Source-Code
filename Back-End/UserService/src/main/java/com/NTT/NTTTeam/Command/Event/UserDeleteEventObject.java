package com.NTT.NTTTeam.Command.Event;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class UserDeleteEventObject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
