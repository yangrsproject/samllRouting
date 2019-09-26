package com.small.routing.smallrouting.mapper;

import com.small.routing.smallrouting.entity.PhotoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PhotoInfoDAO {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phone_name", column = "phone_name"),
            @Result(property = "path", column = "path"),
    })

    @Insert("insert into s_photo_info(phone_name, path) " +
            "values(#{phone_name}, #{path})")
    void insert(PhotoInfo photoInfo);

    @Select("select phone_name, path from s_photo_info " +
            "where open_id = #{open_id}")
    PhotoInfo getUserbyOpenid(String open_id);
}
