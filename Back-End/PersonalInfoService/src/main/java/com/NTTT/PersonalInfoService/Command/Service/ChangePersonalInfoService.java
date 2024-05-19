package com.NTTT.PersonalInfoService.Command.Service;


import com.NTTT.PersonalInfoService.Command.Command.CreatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.UpdatePhysicCharsWeightCommandObject;
import com.NTTT.PersonalInfoService.Command.Model.AddWeightTrackRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ChangePersonalInfoRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.ResponseObject;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Service
public class ChangePersonalInfoService {

    @Autowired
    CommandGateway commandGateway;

    Logger logger
            = LoggerFactory.getLogger(ChangePersonalInfoRequestDTO.class);

    public ResponseObject changePersonalInfo(ChangePersonalInfoRequestDTO changePersonalInfoRequestDTO)
    {
        String physicsCharId = UUID.randomUUID().toString();
        CreatePhysicCharsCommandObject createPhysicCharsCommandObject = new CreatePhysicCharsCommandObject(changePersonalInfoRequestDTO.getUserId(), physicsCharId , changePersonalInfoRequestDTO.getAge(),changePersonalInfoRequestDTO.getSex(),changePersonalInfoRequestDTO.getHeight(),changePersonalInfoRequestDTO.getWeightGoal(),changePersonalInfoRequestDTO.getGoal(),changePersonalInfoRequestDTO.getDietType(),changePersonalInfoRequestDTO.getActivityLevel(),changePersonalInfoRequestDTO.getWeightTracks());
        commandGateway.sendAndWait(createPhysicCharsCommandObject);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setChangeSuccessfully(true);
        responseObject.setMessage("Created PersonalInfo");
        responseObject.setStatusCode(200);
        return  responseObject;
    }

    public ResponseObject updateWeightTracks( AddWeightTrackRequestDTO addWeightTrackRequestDTO)
    {
        UpdatePhysicCharsWeightCommandObject updatePhysicCharsWeightCommandObject = new UpdatePhysicCharsWeightCommandObject(addWeightTrackRequestDTO.getPhysicCharsId(),addWeightTrackRequestDTO.getWeightTracks());
        commandGateway.sendAndWait(updatePhysicCharsWeightCommandObject);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setChangeSuccessfully(true);
        responseObject.setMessage("Added new WeightTrack");
        responseObject.setStatusCode(200);
        return  responseObject;
    }

}
