package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Category;
import com.hbtcsrzzx.ssm.po.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CategoryMapper {
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    @Select("select id from category where state <> -1 and name = #{cname} ")
	Integer findCategoryBycname(String cname);

}