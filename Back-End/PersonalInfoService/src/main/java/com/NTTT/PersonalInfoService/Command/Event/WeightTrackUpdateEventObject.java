package com.NTTT.PersonalInfoService.Command.Event;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class WeightTrackUpdateEventObject {
    @Column(name = "id")
    private String id;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "currentWeight")
    private String currentWeight;

    @Column(name = "pyChId")
    private Integer pyChId;

}
