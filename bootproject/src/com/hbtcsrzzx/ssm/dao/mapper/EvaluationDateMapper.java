package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.*;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EvaluationDateMapper {
	int countByExample(EvaluationDateExample example);

	int deleteByExample(EvaluationDateExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(EvaluationDate record);

	int insertSelective(EvaluationDate record);

	List<EvaluationDate> selectByExample(EvaluationDateExample example);

	EvaluationDate selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") EvaluationDate record,
			@Param("example") EvaluationDateExample example);

	int updateByExample(@Param("record") EvaluationDate record, @Param("example") EvaluationDateExample example);

	int updateByPrimaryKeySelective(EvaluationDate record);

	int updateByPrimaryKey(EvaluationDate record);

	@Select("select * from evaluation_date where state <> -1 and id=#{id}")
	@Results({

			@Result(id = true, column = "id", property = "id"),
			@Result(column = "test_date", property = "testDate"),
			@Result(column = "time_frame", property = "timeFrame"),
			@Result(column = "sid", property = "sid"),
			@Result(column = "lid", property = "lid"),
			@Result(column = "cid", property = "cid")

			})
	EvaluationDate findEvaluationDateById(Integer id);

	@Select("select * from evaluation_date where state <> -1 and lid = #{lid} and sid = #{sid} and cid = #{cid} group by test_date")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "test_date", property = "testDate"),
			@Result(column = "time_frame", property = "timeFrame")
			})
	List<EvaluationDate> findEnrolDate(@Param("lid") Integer lid, @Param("sid") Integer sid, @Param("cid") Integer cid);
	
	
	@Select("select test_date from evaluation_date where state <> -1  GROUP BY test_date")
	@Results({
		@Result(column = "test_date", property = "testDate")
		})
	List<EvaluationDate> findTestDateGroupBy();

	@Select("select * from level where state <> -1 and  id in(select lid from evaluation_date where state <> -1 and cid = #{cid} and sid = #{sid}) ")
	List<Level> findLevelByCidAndSid(@Param("cid")Integer cid, @Param("sid")Integer sid);

	@Select("select * from category where state <> -1 and id in(select cid from evaluation_date where state <> -1 group by cid) ")
	List<Category> findCategoryGroupBy();

	@Select("select * from `subject` where state <> -1 and id in (select sid from evaluation_date where state <> -1 and cid= " +
			"(select id from category where state <> -1 and name = #{cname})) ")
	List<Subject> findSubjectByCname(String cname);

	@Select("select id ,time_frame from evaluation_date where state <> -1 and test_date = #{testDate}  and  lid = #{lid} and sid = #{sid} and cid = #{cid}")
	   @Results({
			   @Result(column = "time_frame",property = "timeFrame")
	   })
	List<EvaluationDate> findEvaluationDateByLidAndCidAndSidAndTestDate(@Param("lid") Integer lid,@Param("sid")  Integer sid,
			@Param("cid")  Integer cid, @Param("testDate") String testDate);

	@Select("select id from evaluation_date where state <> -1 and lid = #{lid} and sid = #{sid} "
			+ "and cid = #{cid} and test_date = #{testDate}  and time_frame = #{timeFrame}")
	Integer findIsRepeat(EvaluationDate evaluationDate);
}