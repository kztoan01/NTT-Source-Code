package com.NTTT.PersonalInfoService.Command.Service;


import com.NTTT.PersonalInfoService.Command.Command.CreatePhysicCharsCommandObject;
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
        logger.info(changePersonalInfoRequestDTO.getActivityLevel().getActivityName());
        CreatePhysicCharsCommandObject createPhysicCharsCommandObject = new CreatePhysicCharsCommandObject(changePersonalInfoRequestDTO.getUserId(), UUID.randomUUID().toString(), changePersonalInfoRequestDTO.getAge(),changePersonalInfoRequestDTO.getSex(),changePersonalInfoRequestDTO.getHeight(),changePersonalInfoRequestDTO.getWeightGoal(),changePersonalInfoRequestDTO.getGoal(),changePersonalInfoRequestDTO.getActivityLevel(),changePersonalInfoRequestDTO.getWeightTracks());
        commandGateway.sendAndWait(createPhysicCharsCommandObject);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setChangeSuccessfully(true);
        responseObject.setMessage("Created PersonalInfo");
        responseObject.setStatusCode(200);
        return  responseObject;
    }

}
