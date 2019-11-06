package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstageTopPermission;
import com.hbtcsrzzx.ssm.po.BackstageTopPermissionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BackstageTopPermissionMapper {
    int countByExample(BackstageTopPermissionExample example);

    int deleteByExample(BackstageTopPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackstageTopPermission record);

    int insertSelective(BackstageTopPermission record);

    List<BackstageTopPermission> selectByExample(BackstageTopPermissionExample example);

    BackstageTopPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackstageTopPermission record, @Param("example") BackstageTopPermissionExample example);

    int updateByExample(@Param("record") BackstageTopPermission record, @Param("example") BackstageTopPermissionExample example);

    int updateByPrimaryKeySelective(BackstageTopPermission record);

    int updateByPrimaryKey(BackstageTopPermission record);

    @Select("select * from backstage_top_permission where state <> -1")
    List<BackstageTopPermission> findAllBackstageTopPermission(Integer page, Integer limit);

    @Select("select * from backstage_top_permission where id = #{id}")
    BackstageTopPermission findBackstageTopPermissionById(Integer id);
}