package com.NTTT.PersonalInfoService.Command.Event;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ActivityLevelUpdateEventObject {
    private String id;

    @Column(name = "ActivityName")
    private String ActivityName;

    @Column(name = "ActivityType")
    private Integer ActivityType;

}
