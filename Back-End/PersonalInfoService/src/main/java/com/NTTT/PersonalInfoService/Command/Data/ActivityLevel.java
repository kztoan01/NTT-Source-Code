package com.NTTT.PersonalInfoService.Command.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;


@Entity
@Table(name = "ActivityLevel")
public class ActivityLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ActivityName")
    private String ActivityName;

    @Column(name = "ActivityType")
    private Integer ActivityType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pyChId")
    @JsonBackReference
    private PhysicChars physicChars;


    public ActivityLevel() {
    }

    public ActivityLevel(Integer id, String activityName, Integer activityType, PhysicChars physicChars) {
        this.id = id;
        ActivityName = activityName;
        ActivityType = activityType;
        this.physicChars = physicChars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public Integer getActivityType() {
        return ActivityType;
    }

    public void setActivityType(Integer activityType) {
        ActivityType = activityType;
    }

    public PhysicChars getPhysicChars() {
        return physicChars;
    }

    public void setPhysicChars(PhysicChars physicChars) {
        this.physicChars = physicChars;
    }
}
