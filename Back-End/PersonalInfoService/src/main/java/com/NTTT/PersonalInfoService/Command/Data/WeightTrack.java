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
@Table(name = "WeightTrack")
public class WeightTrack {
    @Column(name = "id")
    private String id;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "currentWeight")
    private String currentWeight;

    @Column(name = "pyChId")
    private Integer pyChId;

}
