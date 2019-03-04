package com.small.routing.smallrouting.ser_impl;

import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import com.small.routing.smallrouting.mapper.LoginMsgDao;
import com.small.routing.smallrouting.ser_inter.LoginMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginMsgServiceImp implements LoginMsgService {

    @Autowired
    private LoginMsgDao loginMsgDao;

    @Override
    public void insert(LoginSendMsgEntity loginSendMsgEntity) {
        loginMsgDao.insert(loginSendMsgEntity);
    }
}
