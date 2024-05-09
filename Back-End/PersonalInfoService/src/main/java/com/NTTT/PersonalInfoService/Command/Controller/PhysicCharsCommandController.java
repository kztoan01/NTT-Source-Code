package com.NTTT.PersonalInfoService.Command.Controller;


import com.NTTT.PersonalInfoService.Command.Model.AddWeightTrackRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ChangePersonalInfoRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ResponseObject;
import com.NTTT.PersonalInfoService.Command.Service.ChangePersonalInfoService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personalInfo")
public class PhysicCharsCommandController {

    @Autowired
    ChangePersonalInfoService changePersonalInfoService;

    @PostMapping("/changePersonalInfo")
    public ResponseObject changePersonalInfo(@RequestBody ChangePersonalInfoRequestDTO changePersonalInfoRequestDTO)
    {
       return changePersonalInfoService.changePersonalInfo(changePersonalInfoRequestDTO);
    }

    @PostMapping("/updateWeightTracks")
    public ResponseObject updateWeightTracks(@RequestBody AddWeightTrackRequestDTO addWeightTrackRequestDTO)
    {
        return changePersonalInfoService.updateWeightTracks(addWeightTrackRequestDTO);
    }
}
