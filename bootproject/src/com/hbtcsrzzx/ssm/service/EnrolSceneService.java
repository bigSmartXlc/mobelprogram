package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.City;
import com.hbtcsrzzx.ssm.po.EnrolScene;

public interface EnrolSceneService {

	List<EnrolScene> findAllEnrolScene(Integer pageNum, Integer pageSize);

	int getCount();

	int addEnrolScene(EnrolScene enrolScene) throws Exception;

	int updateEnrolSceneById(EnrolScene enrolScene) throws Exception;

	EnrolScene findEnrolSceneById(int id) throws Exception;

	EnrolScene findEnrolSceneBySceneName(String sceneName);

	EnrolScene findEnrolSceneByDateId(Integer dateId);

	List<EnrolScene> findSceneBytestDate(String testDate);

    List<City> findEnrolSceneByCity(String testDate);

	List<EnrolScene> findEnrolSceneByCityAndTestDate(String testDate,Integer cityId);
}
