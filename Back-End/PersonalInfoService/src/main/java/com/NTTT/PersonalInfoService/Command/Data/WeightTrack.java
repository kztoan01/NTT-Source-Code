package com.NTTT.PersonalInfoService.Command.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;

@Builder
@Entity
@Table(name = "WeightTrack")
public class WeightTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "currentWeight")
    private String currentWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pyChId")
    private PhysicChars physicChars;


    public WeightTrack(Integer id, String recordDate, String currentWeight, PhysicChars physicChars) {
        this.id = id;
        this.recordDate = recordDate;
        this.currentWeight = currentWeight;
        this.physicChars = physicChars;
    }

    public WeightTrack() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public PhysicChars getPhysicChars() {
        return physicChars;
    }

    public void setPhysicChars(PhysicChars physicChars) {
        this.physicChars = physicChars;
    }
}
