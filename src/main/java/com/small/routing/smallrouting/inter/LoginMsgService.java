package com.small.routing.smallrouting.inter;

import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import com.small.routing.smallrouting.entity.SLoginmsgInfo;

import java.util.List;

public interface LoginMsgService {
    void insert(LoginSendMsgEntity loginSendMsgEntity);
    List<SLoginmsgInfo> selectAll();
}
