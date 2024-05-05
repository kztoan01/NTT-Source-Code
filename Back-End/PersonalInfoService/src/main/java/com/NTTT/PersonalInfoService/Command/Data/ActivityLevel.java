package com.NTTT.PersonalInfoService.Command.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ActivityLevel")
public class ActivityLevel {
    @Column(name = "id")
    private String id;

    @Column(name = "ActivityName")
    private String ActivityName;

    @Column(name = "ActivityType")
    private Integer ActivityType;
}
