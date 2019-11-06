package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.BackstagePermissionExample;
import com.hbtcsrzzx.ssm.po.queryVo.ChildPermission;
import com.hbtcsrzzx.ssm.po.queryVo.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BackstagePermissionMapper {
    int countByExample(BackstagePermissionExample example);

    int deleteByExample(BackstagePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackstagePermission record);

    int insertSelective(BackstagePermission record);

    List<BackstagePermission> selectByExample(BackstagePermissionExample example);

    BackstagePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackstagePermission record, @Param("example") BackstagePermissionExample example);

    int updateByExample(@Param("record") BackstagePermission record, @Param("example") BackstagePermissionExample example);

    int updateByPrimaryKeySelective(BackstagePermission record);

    int updateByPrimaryKey(BackstagePermission record);

    @Select("select * from backstage_permission where state <> -1 order by upper_id")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "upper_id", property = "backstageTopPermission",one =
            @One(select = ("com.hbtcsrzzx.ssm.dao.mapper.BackstageTopPermissionMapper.findBackstageTopPermissionById")))
    })
    List<BackstagePermission> findAllBackstagePermission();

    @Select("select * from backstage_permission where state <> -1 and id = #{id} ")
    BackstagePermission findBackstagePermissionById(Integer id);

    @Select("select * from backstage_top_permission")
    @Results({
            @Result(column = "id", property = "id",id = true),
            @Result(column = "menu_name", property = "title"),
            @Result(column = "id", property = "children", many =
            @Many(select = ("com.hbtcsrzzx.ssm.dao.mapper.BackstagePermissionMapper.findChildPermissionListByParentId")))
    }
    )
    List<Permission> findBackstagePermissionList();


    @Select("select * from backstage_permission where state <> -1 and upper_id = #{upperId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "permission_name", property = "title"),
    })
    List<ChildPermission> findChildPermissionListByParentId(Integer upperId);
}