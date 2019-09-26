package com.small.routing.smallrouting.impl;

import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import com.small.routing.smallrouting.entity.SLoginmsgInfo;
import com.small.routing.smallrouting.mapper.LoginMsgDao;
import com.small.routing.smallrouting.inter.LoginMsgService;
import com.small.routing.smallrouting.mapper.SLoginmsgInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginMsgServiceImp implements LoginMsgService {

    @Autowired
    private LoginMsgDao loginMsgDao;

    @Autowired
    private SLoginmsgInfoDao sLoginmsgInfoDao;

    @Override
    public void insert(LoginSendMsgEntity loginSendMsgEntity) {
        loginMsgDao.insert(loginSendMsgEntity);
    }

    @Override
    public List<SLoginmsgInfo> selectAll() {
        return sLoginmsgInfoDao.selectAll();
    }
}
