package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.ThirdLogin;
import com.hbtcsrzzx.ssm.po.ThirdLoginExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ThirdLoginMapper {
    int countByExample(ThirdLoginExample example);

    int deleteByExample(ThirdLoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    List<ThirdLogin> selectByExample(ThirdLoginExample example);

    ThirdLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ThirdLogin record, @Param("example") ThirdLoginExample example);

    int updateByExample(@Param("record") ThirdLogin record, @Param("example") ThirdLoginExample example);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);

    @Select("select id ,user_id,platform,openid,openname from third_login where openid = #{openid} and platform =#{platform}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_id", property = "userId")
    })
    ThirdLogin findThirdLoginByOpenid(@Param("openid") String openid, @Param("platform") String platform);
}