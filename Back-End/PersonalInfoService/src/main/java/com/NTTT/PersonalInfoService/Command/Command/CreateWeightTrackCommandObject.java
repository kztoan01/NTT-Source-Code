package com.NTTT.PersonalInfoService.Command.Command;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateWeightTrackCommandObject {
    @Column(name = "id")
    private String id;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "currentWeight")
    private String currentWeight;

    @Column(name = "pyChId")
    private Integer pyChId;

}
