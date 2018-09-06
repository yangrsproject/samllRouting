package com.small.routing.smallrouting.ser_inter;

import com.small.routing.smallrouting.entity.UserEntity;

public interface UserService {

    /**
     * 获取用户
     * @param open_id
     * @return
     */
    UserEntity getUser(String open_id);

    /**
     * 插入用户信息
     * @param userEntity
     */
    void insUser(UserEntity userEntity);
}
