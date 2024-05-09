package com.NTTT.PersonalInfoService.Command.Event;

import com.NTTT.PersonalInfoService.Command.Data.ActivityLevel;
import com.NTTT.PersonalInfoService.Command.Data.WeightTrack;
import lombok.Data;

import java.util.List;

@Data
public class PhysicCharsUpdateWeightTrackEventObject {

    private String physicCharsId;

    private List<WeightTrack> weightTracks;
}
