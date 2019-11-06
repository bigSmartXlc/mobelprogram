package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.BackstageRolePermission;
import com.hbtcsrzzx.ssm.po.BackstageRolePermissionExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BackstageRolePermissionMapper {
    int countByExample(BackstageRolePermissionExample example);

    int deleteByExample(BackstageRolePermissionExample example);

    int insert(BackstageRolePermission record);

    int insertSelective(BackstageRolePermission record);

    List<BackstageRolePermission> selectByExample(BackstageRolePermissionExample example);

    int updateByExampleSelective(@Param("record") BackstageRolePermission record, @Param("example") BackstageRolePermissionExample example);

    int updateByExample(@Param("record") BackstageRolePermission record, @Param("example") BackstageRolePermissionExample example);

    /**
     * 根据角色id，查询对应的权限列表
     * @param roleId
     * @return
     */
    @Select("select * from backstage_permission where state <> -1 and id in(select permission_id from backstage_role_permission where state <> -1 and role_id = #{roleId})")
    List<BackstagePermission> findBackstageRolePermissionByRoleId(Integer roleId);

    @Delete("delete from backstage_role_permission where role_id = #{roleId}")
    int delRoleAndPermissionByRoleId(Integer roleId);
}