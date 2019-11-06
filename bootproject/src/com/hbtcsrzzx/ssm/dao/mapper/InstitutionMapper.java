package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Institution;
import com.hbtcsrzzx.ssm.po.InstitutionExample;
import com.hbtcsrzzx.ssm.po.InstitutionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InstitutionMapper {
    int countByExample(InstitutionExample example);

    int deleteByExample(InstitutionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InstitutionWithBLOBs record);

    int insertSelective(InstitutionWithBLOBs record);

    List<InstitutionWithBLOBs> selectByExampleWithBLOBs(InstitutionExample example);

    List<Institution> selectByExample(InstitutionExample example);

    InstitutionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InstitutionWithBLOBs record, @Param("example") InstitutionExample example);

    int updateByExampleWithBLOBs(@Param("record") InstitutionWithBLOBs record, @Param("example") InstitutionExample example);

    int updateByExample(@Param("record") Institution record, @Param("example") InstitutionExample example);

    int updateByPrimaryKeySelective(InstitutionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InstitutionWithBLOBs record);

    int updateByPrimaryKey(Institution record);

    @Select("select id,image,name,phone,addr  from institution  where state <> -1 limit #{start} , #{limit} ")
	List<InstitutionWithBLOBs> findAllInstitutionBasicInformation(@Param("start")Integer start, @Param("limit")Integer limit);
}