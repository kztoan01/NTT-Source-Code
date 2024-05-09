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

    @Column(name = "activityName")
    private String activityName;

    @Column(name = "activityType")
    private Integer activityType;

    @OneToOne(mappedBy = "activityLevel")
    private PhysicChars physicChars;

    public ActivityLevel(Integer id, String activityName, Integer activityType, PhysicChars physicChars) {
        this.id = id;
        this.activityName = activityName;
        this.activityType = activityType;
        this.physicChars = physicChars;
    }

    public ActivityLevel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public PhysicChars getPhysicChars() {
        return physicChars;
    }

    public void setPhysicChars(PhysicChars physicChars) {
        this.physicChars = physicChars;
    }
}
