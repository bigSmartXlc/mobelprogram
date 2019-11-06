package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import com.hbtcsrzzx.ssm.po.EnrolExamineeExample;
import com.hbtcsrzzx.ssm.po.EnrolScene;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface EnrolExamineeMapper {
    int countByExample(EnrolExamineeExample example);

    int deleteByExample(EnrolExamineeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EnrolExaminee record);

    int insertSelective(EnrolExaminee record);

    List<EnrolExaminee> selectByExample(EnrolExamineeExample example);

    EnrolExaminee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EnrolExaminee record, @Param("example") EnrolExamineeExample example);

    int updateByExample(@Param("record") EnrolExaminee record, @Param("example") EnrolExamineeExample example);

    int updateByPrimaryKeySelective(EnrolExaminee record);

    int updateByPrimaryKey(EnrolExaminee record);


    @Select( "<script> select * from enrol_examinee where state &lt;&gt; -1 <when test='examineeName != null '> and name like '%${examineeName}%' </when> "
            + " <when test='searchCity != null'> and enrol_scene_id in(select id from enrol_scene where state &lt;&gt; -1 and city_id=#{searchCity }) </when> "+
            " <when test='searchAuditStatus != null'> and audit_status = #{searchAuditStatus} </when> "+
            "<when test='searchDate != null'> and enrol_scene_id in(SELECT id FROM enrol_scene where state &lt;&gt; -1 and date_id in(SELECT id FROM evaluation_date  WHERE state &lt;&gt; -1 and test_date =#{searchDate})) </when>"+
            " limit #{pageNum} , #{pageSize} </script>")
    @Results(id = "examineeResult",value={
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "nationality", property = "nationality"),
            @Result(column = "nation", property = "nation"),
            @Result(column = "examination_card", property = "examinationCard"),
            @Result(column = "id_card", property = "idCard"),
            @Result(column = "unit", property = "unit"),
            @Result(column = "evaluation_address", property = "evaluationAddress"),
            @Result(column = "recommend_unit", property = "recommendUnit"),
            @Result(column = "exam_site_number", property = "examSiteNumber"),
            @Result(column = "seat_number", property = "seatNumber"),
            @Result(column = "pay_status", property = "payStatus"),
            @Result(column = "audit_status", property = "auditStatus"),
            @Result(column = "take_care_matters", property = "takeCareMatters"),
            @Result(column = "state", property = "state"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "category", property = "category"),
            @Result(column = "subject", property = "subject"),
            @Result(column = "level", property = "level"),
            @Result(column = "examination_results", property = "examinationResults"),
            @Result(column = "institution_name", property = "institutionName"),
            @Result(column = "institution_nature", property = "institutionNature"),
            @Result(column = "order_id",property = "orderId"),

            @Result(column = "enrol_scene_id", property = "enrolSceneId"),
            @Result(column = "user_log_phone", property = "userLogPhone"),

            //关联查询场次信息
            @Result(column="enrol_scene_id",javaType = EnrolScene.class,property="enrolScene",
                    one=@One(select=("com.hbtcsrzzx.ssm.dao.mapper.EnrolSceneMapper.findEnrolSceneById")))


    })
    List<EnrolExaminee> findAllEnrolExaminee(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,
                                             @Param("examineeName")String examineeName,@Param("searchCity")String searchCity,@Param("searchAuditStatus")String searchAuditStatus,
                                             @Param("searchDate")String searchDate);


    @Select("select * from enrol_examinee where state <> -1 and user_log_phone = #{phone} ")
    @ResultMap("examineeResult")
    List<EnrolExaminee> findEnrolExamineeByPhone(@Param("phone")String phone);

    @Select("select * from enrol_examinee where  examination_results <> 0 and "
            + "state = 1 and pay_status = 1 and audit_status =1 and examination_card=#{examinationCard}")
    @ResultMap("examineeResult")
    EnrolExaminee findEnrolExamineeByExaminationCard(String examinationCard);

    @Select("select * from  enrol_examinee where state <> -1 and id = #{id}")
    @ResultMap("examineeResult")
    EnrolExaminee findEnrolExamineeById(int id);

    @Select("select * from enrol_examinee where state <> -1 and order_id = #{orderId}")
    @ResultMap("examineeResult")
    EnrolExaminee findEnrolExamineeByOrderId(Long orderId);

    @Select("select * from enrol_examinee where create_time <= #{date} and state <> -1 and pay_status = #{payStatus}  ")
    @ResultMap("examineeResult")
    List<EnrolExaminee> findEnrolExamineeByTimeout(@Param("payStatus")Integer payStatus, @Param("date")Date date);
}