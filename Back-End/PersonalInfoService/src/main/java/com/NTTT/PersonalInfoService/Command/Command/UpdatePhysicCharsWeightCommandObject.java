package com.NTTT.PersonalInfoService.Command.Command;

import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
public class UpdatePhysicCharsWeightCommandObject {

    @TargetAggregateIdentifier
    private String physicCharsId;


    private List<WeightTrack> weightTracks;

    public UpdatePhysicCharsWeightCommandObject(String physicCharsId, List<WeightTrack> weightTracks) {
        this.physicCharsId = physicCharsId;
        this.weightTracks = weightTracks;
    }
}
