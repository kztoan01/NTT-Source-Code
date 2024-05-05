package com.NTTT.PersonalInfoService.Command.Event;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class PremiumPlanCreateEventObject {
    @Column(name = "id")
    private String id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "premiumType")
    private String premiumType;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;
}
