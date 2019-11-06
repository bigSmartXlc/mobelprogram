package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.ssm.po.BackstageUserExample;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface BackstageUserMapper {
    int countByExample(BackstageUserExample example);

    int deleteByExample(BackstageUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackstageUser record);

    int insertSelective(BackstageUser record);

    List<BackstageUser> selectByExample(BackstageUserExample example);

    BackstageUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackstageUser record, @Param("example") BackstageUserExample example);

    int updateByExample(@Param("record") BackstageUser record, @Param("example") BackstageUserExample example);

    int updateByPrimaryKeySelective(BackstageUser record);

    int updateByPrimaryKey(BackstageUser record);

    @Select("select * from backstage_user where state <> -1 limit #{startPage} , #{limit} ")
    List<BackstageUser> findAllBackstageUser(@Param("startPage") Integer startPage, @Param("limit") Integer limit);

    @Select("select count(id) from backstage_user where state <> #{delState} ")
    Integer getCount(int delState);

    @Select("select  * from backstage_user where state <> -1 and id = #{id}")
    @Results(id = "generalResults", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_logo", property = "userLogo", id = true),
            @Result(column = "id", property = "backstageRoles", javaType = List.class, many = @Many(select = (
                    "com.hbtcsrzzx.ssm.dao.mapper.BackstageRoleMapper.findRoleByUserid")))
    })
    BackstageUser findBackstageUserById(Integer id);

    @Insert("insert into backstage_user values(null,#{username},#{password},#{name},#{contact},#{state},#{createtime},#{updatetime})")
    int addBackstageUser(BackstageUser backstageUser);

    @Insert("insert into backstage_user_role values(#{userId},#{roleId},#{state},#{date},#{date})")
    int saveUserAndRoleByRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId, @Param("date") Date date, @Param("state") Integer state);

    @Delete("delete from backstage_user_role where user_id = #{id}")
    int delUserAndRoleByUserId(Integer id);


    @Select("select * from backstage_user where  state <> #{state} and username = #{username}")
    @ResultMap("generalResults")
    BackstageUser findBackstageUserByUserName(@Param("state") Integer state, @Param("username") String username);

    @Select(" select * from backstage_user where state <> #{state} and id in (select user_id from backstage_user_role where role_id in " +
            "(select id from backstage_role where role_name = #{roleName}))")
    List<BackstageUser> findBackstageUsersByRoleName(@Param("state") Integer state, @Param("roleName") String roleName);

    /**
     * 查询业务员提成比例
     * @param id 业务员用户id
     * @return 所有的分成信息
     */
    @Select("select e.name , e.cost , e.institution_nature  from enrol_examinee e ,royalty r " +
            " where r.state <> -1 and r.state <> -1 and r.backstage_user_id = #{id}  and e.id = r.enrol_examinee_id")
    @Results({
            @Result(column = "institution_nature", property = "returnRatio", one = @One(
                    select = ("com.hbtcsrzzx.ssm.dao.mapper.SharingMapper.findSalesmanCommissionByInstitutionalNature")))
    }
    )
    List<Royalty> findSalesmanRoyalty(Integer id);


    /**
     * 查询机构分成比例
     *
     * @param id 机构用户id
     * @return 所有的分成信息
     */
    @Select("select e.name , e.cost , e.institution_nature  from enrol_examinee e ,royalty r " +
            " where r.state <> -1 and r.state <> -1 and r.backstage_user_id = #{id}  and e.id = r.enrol_examinee_id")
    @Results({
            @Result(column = "institution_nature", property = "returnRatio", one = @One(
                    select = ("com.hbtcsrzzx.ssm.dao.mapper.SharingMapper.findInstitutionByInstitutionalNature")))
    })
    List<Royalty> findInstitutionRoyalty(Integer id);


    /**
     * 查询出角色是机构并且没有绑定机构的用户列表
     * @return 用户列表
     */
    @Select("select * from backstage_user where state <> -1 and id in(" +
            "select user_id from backstage_user_role where state <> -1 and role_id in (" +
            "select id from   backstage_role where state <> -1 and role_name = 'INSTITUTION'" +
            ")) and  not exists( select * from backstage_user_and_institution where state <> -1 and  backstage_user.id =backstage_user_and_institution. backstage_user ) ")
    List<BackstageUser> findBackstageUserAllByInstitution();

    /**
     * 新增用户角色对应关系
     * @param userId    用户id
     * @param roleName  角色名称
     * @param newdate  创建时间
     * @param updateDate  修改时间
     */
    @Insert("insert into backstage_user_role values (#{userId},(select id from backstage_role where role_name = #{roleName}),0,#{newdate},#{updateDate})")
    void addUserAndRoleByRoleName(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("newdate")Date newdate, @Param("updateDate") Date updateDate);


    /**
     * 查询该机构对应的用户列表
     * @param institutionId
     * @return
     */
    @Select("select * from backstage_user where state <> -1 and id in (select backstage_user from backstage_user_and_institution where state <> -1 and institution_id = #{institution_id})")
   @ResultMap("generalResults")
    List<BackstageUser> findBackstageUserByinstitutionId(Integer institutionId);
}