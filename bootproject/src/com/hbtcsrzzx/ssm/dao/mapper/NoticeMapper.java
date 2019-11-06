package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Notice;
import com.hbtcsrzzx.ssm.po.NoticeExample;
import com.hbtcsrzzx.ssm.po.NoticeWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
    int countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeWithBLOBs record);

    int insertSelective(NoticeWithBLOBs record);

    List<NoticeWithBLOBs> selectByExampleWithBLOBs(NoticeExample example);

    List<Notice> selectByExample(NoticeExample example);

    NoticeWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticeWithBLOBs record, @Param("example") NoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticeWithBLOBs record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(NoticeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NoticeWithBLOBs record);

    int updateByPrimaryKey(Notice record);
}