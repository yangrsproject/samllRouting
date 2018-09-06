package com.small.routing.smallrouting.control;

import com.small.routing.common.ResponseMsg;
import com.small.routing.smallrouting.entity.UserEntity;
import com.small.routing.smallrouting.ser_inter.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserConroller {

    private final Logger logger = LogManager.getLogger(UserConroller.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/initUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg initUser(@RequestBody UserEntity user){
        logger.info("---------------------------------");
        userService.insUser(user);
        return  new ResponseMsg(HttpStatus.OK.value(), "SUCCESS");
    }
}
