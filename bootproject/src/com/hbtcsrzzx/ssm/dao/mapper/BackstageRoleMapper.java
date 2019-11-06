package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface BackstageRoleMapper {
    int countByExample(BackstageRoleExample example);

    int deleteByExample(BackstageRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackstageRole record);

    int insertSelective(BackstageRole record);

    List<BackstageRole> selectByExample(BackstageRoleExample example);

    BackstageRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackstageRole record, @Param("example") BackstageRoleExample example);

    int updateByExample(@Param("record") BackstageRole record, @Param("example") BackstageRoleExample example);

    int updateByPrimaryKeySelective(BackstageRole record);

    int updateByPrimaryKey(BackstageRole record);

    @Select("select * from backstage_role where state <> -1 limit #{startPage} , #{limit} ")
    @Results({
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc")
    })
    List<BackstageRole> findAllBackstageRole(@Param("startPage") Integer startPage, @Param("limit") Integer limit);

    @Select("select count(id) from backstage_role where state <> #{delState} ")
    Integer getCount(int delState);

    @Select("select  * from backstage_role where state <> -1 and id = #{id}")
    @Results({
            @Result(property = "backstagePermission", column = "id", javaType = List.class,

                    many = @Many(select = ("com.hbtcsrzzx.ssm.dao.mapper.BackstageRolePermissionMapper.findBackstageRolePermissionByRoleId")))
    })
    BackstageRole findBackstageRoleById(Integer id);

    @Insert("insert into backstage_role values(null,#{roleName},#{roleDesc},#{state},#{createTime},#{updateTime})")
    int addBackstageRole(BackstageRole backstageRole);

    @Select("select * from backstage_role where id in(select role_id from backstage_user_role where user_id = #{userId})")
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "backstagePermission",javaType = List.class,
                    many = @Many(select = ("com.hbtcsrzzx.ssm.dao.mapper.BackstageRolePermissionMapper.findBackstageRolePermissionByRoleId")))
    })
    List<BackstageRole> findRoleByUserid(Integer userId);
}