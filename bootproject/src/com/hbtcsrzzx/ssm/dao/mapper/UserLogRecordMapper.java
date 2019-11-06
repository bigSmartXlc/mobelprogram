package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.po.UserLogRecord;
import com.hbtcsrzzx.ssm.po.UserLogRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface UserLogRecordMapper {
    int countByExample(UserLogRecordExample example);

    int deleteByExample(UserLogRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLogRecord record);

    int insertSelective(UserLogRecord record);

    List<UserLogRecord> selectByExample(UserLogRecordExample example);

    UserLogRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLogRecord record, @Param("example") UserLogRecordExample example);

    int updateByExample(@Param("record") UserLogRecord record, @Param("example") UserLogRecordExample example);

    int updateByPrimaryKeySelective(UserLogRecord record);

    int updateByPrimaryKey(UserLogRecord record);


}