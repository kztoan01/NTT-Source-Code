package com.NTTT.PersonalInfoService.Command.Service;


import com.NTTT.PersonalInfoService.Command.Command.CreatePhysicCharsCommandObject;
import com.NTTT.PersonalInfoService.Command.Command.CreateUserPlanningCommandObject;
import com.NTTT.PersonalInfoService.Command.Config.Enum;
import com.NTTT.PersonalInfoService.Command.Data.UserPlanning;
import com.NTTT.PersonalInfoService.Command.Data.UserPlanningRepository;
import com.NTTT.PersonalInfoService.Command.Model.ChangePersonalInfoRequestDTO;
import com.NTTT.PersonalInfoService.Command.Model.CreateUserPlanningDTO;
import com.NTTT.PersonalInfoService.Command.Model.ResponseObject;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserPlanningService {


    @Autowired
    CommandGateway commandGateway;


    @Autowired
    UserPlanningRepository planningRepository;

    Logger logger
            = LoggerFactory.getLogger(ChangePersonalInfoRequestDTO.class);


    public ResponseObject createUserPlanning(CreateUserPlanningDTO createUserPlanningDTO)
    {

        String userPlanningId = UUID.randomUUID().toString();
        CreateUserPlanningCommandObject createUserPlanningCommandObject = new CreateUserPlanningCommandObject(userPlanningId, createUserPlanningDTO.getProtein(), createUserPlanningDTO.getCarbs(), createUserPlanningDTO.getFat(), createUserPlanningDTO.getCalories(), createUserPlanningDTO.getPlanType());
        commandGateway.sendAndWait(createUserPlanningCommandObject);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setChangeSuccessfully(true);
        responseObject.setMessage("Created UserPlanning");
        responseObject.setStatusCode(200);
        return  responseObject;
    }

    public List<UserPlanning> getUserPlanningByUserID(String id)
    {
        return planningRepository.findByUserId(id);
    }


}
