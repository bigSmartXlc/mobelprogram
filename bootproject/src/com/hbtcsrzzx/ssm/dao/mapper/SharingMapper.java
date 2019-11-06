package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Sharing;
import com.hbtcsrzzx.ssm.po.SharingExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SharingMapper {
    int countByExample(SharingExample example);

    int deleteByExample(SharingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sharing record);

    int insertSelective(Sharing record);

    List<Sharing> selectByExample(SharingExample example);

    Sharing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sharing record, @Param("example") SharingExample example);

    int updateByExample(@Param("record") Sharing record, @Param("example") SharingExample example);

    int updateByPrimaryKeySelective(Sharing record);

    int updateByPrimaryKey(Sharing record);
    @Select("select * from sharing where state <> -1 limit #{startPage} , #{limit} ")
    @Results({
            @Result(column = "institutional_nature",property = "institutionalNature"),
            @Result(column = "salesman_commission",property = "salesmanCommission"),
            @Result(column = "institutional_commission",property = "institutionalCommission")
    })
    List<Sharing> findAllSharing(@Param("startPage")Integer startPage, @Param("limit")Integer limit);

    @Select("select count(id) from  sharing where state <> #{state} ")
    Integer getCount(Integer state);


    @Select("select  * from sharing where state <> -1 and id = #{id} ")
    @Results({
            @Result(column = "institutional_nature",property = "institutionalNature"),
            @Result(column = "salesman_commission",property = "salesmanCommission"),
            @Result(column = "institutional_commission",property = "institutionalCommission")
    })
    Sharing findSharingById(Integer id);

    @Insert("insert into sharing values(null,#{institutionalNature},#{salesmanCommission},#{institutionalCommission},#{state},#{createTime},#{updateTime})")
    int addSharing(Sharing sharing);

    @Select("select salesman_commission from sharing where state <> -1 and institutional_nature = #{institutionalNature}")
    Integer findSalesmanCommissionByInstitutionalNature(String institutionalNature);


    @Select("select institutional_commission from sharing where state <> -1 and institutional_nature = #{institutionalNature}")
    Integer findInstitutionByInstitutionalNature(String institutionalNature);
}