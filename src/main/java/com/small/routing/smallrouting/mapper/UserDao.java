package com.small.routing.smallrouting.mapper;

import com.small.routing.smallrouting.entity.UserEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "open_id", column = "open_id"),
            @Result(property = "nike_name", column = "nike_name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "city", column = "city"),
            @Result(property = "province", column = "province"),
            @Result(property = "country", column = "country"),
            @Result(property = "login_time", column = "login_time"),
    })

    @Insert("insert into user_info(user_id, open_id, nike_name, gender, city, province, country, login_time) " +
            "values(#{user_id}, #{open_id}, #{nike_name}, #{gender}, #{city}, #{province}, #{country}, #{login_time})")
    void insert(UserEntity userEntity);

    @Select("select open_id, nike_name, gender, city, province, country, login_time from user_info " +
            "where open_id = #{open_id}")
    UserEntity getUserbyOpenid(String open_id);
}
