package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.UserAndExaminee;
import com.hbtcsrzzx.ssm.po.UserAndExamineeExample;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserAndExamineeMapper {
    int countByExample(UserAndExamineeExample example);

    int deleteByExample(UserAndExamineeExample example);

    int insert(UserAndExaminee record);

    int insertSelective(UserAndExaminee record);

    List<UserAndExaminee> selectByExample(UserAndExamineeExample example);

    int updateByExampleSelective(@Param("record") UserAndExaminee record, @Param("example") UserAndExamineeExample example);

    int updateByExample(@Param("record") UserAndExaminee record, @Param("example") UserAndExamineeExample example);

    @Select("select " +
            "ee.name ,ee.cost , d.return_proportion as returnRatio , uae.recommender_level as recommenderLevel, ul.distribution_level as distributionLevel " +
            "from user_and_examinee uae " +
            "inner join enrol_examinee ee on uae.examinee_id = ee.id " +
            "inner join user_log ul on uae.user_id = ul.id " +
            "inner join distribution d " +
            "where uae.state <> -1 " +
            "and  ee.state <> -1   " +
            "and ul.state <> -1  " +
            "and uae.user_id = #{userId} " +
            "and d.distribution_level = ul.distribution_level " +
            "and d.recommender_level = uae.recommender_level")
    List<Royalty> queryUserrebate(Integer userId);
}