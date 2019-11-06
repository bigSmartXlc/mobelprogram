package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Syslog;
import com.hbtcsrzzx.ssm.po.SyslogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SyslogMapper {
    int countByExample(SyslogExample example);

    int deleteByExample(SyslogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    List<Syslog> selectByExample(SyslogExample example);

    Syslog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Syslog record, @Param("example") SyslogExample example);

    int updateByExample(@Param("record") Syslog record, @Param("example") SyslogExample example);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKey(Syslog record);
}