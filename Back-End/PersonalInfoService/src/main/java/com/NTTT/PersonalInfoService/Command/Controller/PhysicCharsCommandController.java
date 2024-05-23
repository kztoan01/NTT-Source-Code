package com.NTTT.PersonalInfoService.Command.Controller;


import com.NTTT.PersonalInfoService.Command.Model.AddWeightTrackRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ChangePersonalInfoRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ResponseObject;
import com.NTTT.PersonalInfoService.Command.Model.StringObject;
import com.NTTT.PersonalInfoService.Command.Service.ChangePersonalInfoService;
import com.NTTT.PersonalInfoService.Command.Service.UserPlanningService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger
            = LoggerFactory.getLogger(PhysicCharsCommandController.class);

    @Autowired
    UserPlanningService userPlanningService;

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

    @PostMapping("/findPlanByUserId")
    public ResponseObject findPlanByUserId(@RequestBody StringObject stringObject)
    {
        logger.info(stringObject.getId());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(userPlanningService.getUserPlanningByUserID(stringObject.getId()));
        responseObject.setChangeSuccessfully(false);
        return responseObject;
    }
}
