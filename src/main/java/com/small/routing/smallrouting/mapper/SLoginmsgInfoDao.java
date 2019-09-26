package com.small.routing.smallrouting.mapper;

import com.small.routing.smallrouting.entity.SLoginmsgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Mapper
@Transactional
public interface SLoginmsgInfoDao {
    int deleteByPrimaryKey(Integer msgId);

    int insert(SLoginmsgInfo record);

    int insertSelective(SLoginmsgInfo record);

    SLoginmsgInfo selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(SLoginmsgInfo record);

    int updateByPrimaryKey(SLoginmsgInfo record);

    List<SLoginmsgInfo> selectAll();
}