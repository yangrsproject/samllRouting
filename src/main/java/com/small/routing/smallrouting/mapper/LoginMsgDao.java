package com.small.routing.smallrouting.mapper;

import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface LoginMsgDao {
    @Results({
            @Result(property = "msg_id", column = "msg_id"),
            @Result(property = "phone_num", column = "phone_num"),
            @Result(property = "msg_content", column = "msg_content"),
            @Result(property = "op_time", column = "op_time")
    })

    @Options(useGeneratedKeys = true, keyColumn = "msg_id", keyProperty = "msg_id")
    @Insert("insert into s_loginmsg_info(phone_num, msg_content, op_time) " +
            "values(#{phone_num}, #{msg_content}, #{op_time})")
    void insert(LoginSendMsgEntity loginSendMsgEntity);
}
