package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Content;
import com.hbtcsrzzx.ssm.po.ContentExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ContentMapper {
    int countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    @Select("select * from content where state <> #{state} order by category_id , sort_order   ")
    @Results(id = "contentResult",value = {

            @Result(column = "id",property = "id",id = true),
            @Result(column = "title",property = "title"),
            @Result(column = "url",property = "url"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "state",property = "state"),
            @Result(column = "sort_order",property = "sortOrder"),
            @Result(column = "category_id",property = "categoryId")

    })
    List<Content> findAllContent(int state);

    @Select("select *  from content where state <> #{state} and category_id = #{citegoryId} order by sort_order")
    @ResultMap("contentResult")
    List<Content> findContentByCitegoryId(@Param("citegoryId") Integer citegoryId,@Param("state") int state);
}