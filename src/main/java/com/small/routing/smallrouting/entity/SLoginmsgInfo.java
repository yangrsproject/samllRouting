package com.small.routing.smallrouting.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SLoginmsgInfo {
    private Integer msgId;

    private String phoneNum;

    private String msgContent;

    private String opTime;
}