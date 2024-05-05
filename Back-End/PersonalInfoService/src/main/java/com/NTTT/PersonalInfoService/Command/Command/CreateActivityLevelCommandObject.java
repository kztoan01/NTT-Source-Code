package com.NTTT.PersonalInfoService.Command.Command;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateActivityLevelCommandObject {
    @Column(name = "id")
    private String id;

    @Column(name = "ActivityName")
    private String ActivityName;

    @Column(name = "ActivityType")
    private Integer ActivityType;

}
