package com.NTTT.PersonalInfoService.Command.Model;

import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
public class AddWeightTrackRequestDTO {

    private String physicCharsId;

    private List<WeightTrack> weightTracks;


}
