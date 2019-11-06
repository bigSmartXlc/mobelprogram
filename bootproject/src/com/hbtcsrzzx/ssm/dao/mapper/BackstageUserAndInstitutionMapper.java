package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.ssm.po.BackstageUserAndInstitution;
import com.hbtcsrzzx.ssm.po.BackstageUserAndInstitutionExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BackstageUserAndInstitutionMapper {
    int countByExample(BackstageUserAndInstitutionExample example);

    int deleteByExample(BackstageUserAndInstitutionExample example);

    int insert(BackstageUserAndInstitution record);

    int insertSelective(BackstageUserAndInstitution record);

    List<BackstageUserAndInstitution> selectByExample(BackstageUserAndInstitutionExample example);

    int updateByExampleSelective(@Param("record") BackstageUserAndInstitution record, @Param("example") BackstageUserAndInstitutionExample example);

    int updateByExample(@Param("record") BackstageUserAndInstitution record, @Param("example") BackstageUserAndInstitutionExample example);

    /*@Select("select backstage_user from backstage_user_and_institution where institution_id = #{institutionId} and backstage_user = #{backstageUser} ")
    Integer findIsBackstageUserAndInstitution(BackstageUserAndInstitution backstageUserAndInstitution);*/

    @Select("select * from backstage_user bu " +
            "where state <> -1 and exists " +
            "(select *  from backstage_user_and_institution  bui "+
            "where state <> -1 and institution_id = #{institutionId} and bu.id = bui.backstage_user)")
    @Results(id="user", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "backstageRoles", javaType = List.class, many = @Many(select = (
                    "com.hbtcsrzzx.ssm.dao.mapper.BackstageRoleMapper.findRoleByUserid")))
    })
    List<BackstageUser> findBackstageUserByInstitutionId(Integer institutionId);

    /**
     * 查询该机构对应的用户角色
     * @param institutionId  机构id
     * @return 用户列表
     */
    @Select("select * from backstage_user where state <> -1 and id  in(select backstage_user from backstage_user_and_institution  where state <> -1 and institution_id =#{institutionId})")
    @ResultMap("user")
    List<BackstageUser> findInstitutionIsRoleName(Integer institutionId);

    /**
     * 根据用户id 和机构id  删除这条纪录
     * @param userId  用户id
     * @param institutionId 机构id
     */
    @Delete("delete from backstage_user_and_institution where institution_id = #{institutionId} and backstage_user = #{userId}")
    void deleteUserAndInstitution(@Param("userId") Integer userId, @Param("institutionId")Integer institutionId);
}