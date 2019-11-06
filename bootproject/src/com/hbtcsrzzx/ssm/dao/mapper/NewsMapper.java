package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.News;
import com.hbtcsrzzx.ssm.po.NewsExample;
import com.hbtcsrzzx.ssm.po.NewsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NewsMapper {
    int countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsWithBLOBs record);

    int insertSelective(NewsWithBLOBs record);

    List<NewsWithBLOBs> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExample(NewsExample example);

    NewsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsWithBLOBs record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsWithBLOBs record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(NewsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NewsWithBLOBs record);

    int updateByPrimaryKey(News record);

    @Select("select id,title,date,introduction from news  where state <> -1 ORDER BY date desc limit #{start} , #{limit} ")
	List<NewsWithBLOBs> findAllNewsByTitle(@Param("start")Integer start,@Param("limit") Integer limit);
}