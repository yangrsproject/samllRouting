package com.small.routing.smallrouting.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Setter
@Getter
@ToString
public class LoginSendMsgEntity {
    private String msg_id;
    private String phone_num;
    private String msg_content;
    private Date op_time;
}
