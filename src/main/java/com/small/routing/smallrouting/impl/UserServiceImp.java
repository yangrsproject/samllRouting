package com.small.routing.smallrouting.impl;

import com.small.routing.smallrouting.mapper.UserDao;
import com.small.routing.smallrouting.entity.UserEntity;
import com.small.routing.smallrouting.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUser(String open_id) {
        return null;
    }

    @Override
    public void insUser(UserEntity userEntity) {
        userDao.insert(userEntity);
    }
}
