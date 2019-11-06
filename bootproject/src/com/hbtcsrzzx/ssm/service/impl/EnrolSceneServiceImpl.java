package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import com.hbtcsrzzx.ssm.po.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtcsrzzx.ssm.dao.mapper.CityMapper;
import com.hbtcsrzzx.ssm.dao.mapper.EnrolSceneMapper;
import com.hbtcsrzzx.ssm.dao.mapper.EvaluationDateMapper;
import com.hbtcsrzzx.ssm.po.EnrolScene;
import com.hbtcsrzzx.ssm.po.EnrolSceneExample;
import com.hbtcsrzzx.ssm.po.EnrolSceneExample.Criteria;
import com.hbtcsrzzx.ssm.po.EvaluationDate;
import com.hbtcsrzzx.ssm.service.EnrolSceneService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Service
public class EnrolSceneServiceImpl implements EnrolSceneService {

    @Autowired
    private EnrolSceneMapper enrolSceneMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private EvaluationDateMapper evaluationDateMapper;

    @Override
    public List<EnrolScene> findAllEnrolScene(Integer pageNum, Integer pageSize) {

        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }

        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        int startPage = pageNum - 1;

        return enrolSceneMapper.findAllEnrolScene(startPage, pageSize);
    }

    @Override
    public int getCount() {
        EnrolSceneExample example = new EnrolSceneExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);

        return enrolSceneMapper.countByExample(example);
    }

    @Override
    public int addEnrolScene(EnrolScene enrolScene) throws Exception {
        int res = -1;
        if (enrolScene != null) {
            enrolScene.setState(Constants.NORMAL_STATE);
            enrolScene.setCreateTime(new Date());
            enrolScene.setUpdateTime(new Date());
            res = enrolSceneMapper.insertSelective(enrolScene);
        }
        return res;
    }

    @Override
    public int updateEnrolSceneById(EnrolScene enrolScene) throws Exception {

        int res = -1;
        try {
            enrolScene.setUpdateTime(new Date());
            res = enrolSceneMapper.updateByPrimaryKeySelective(enrolScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public EnrolScene findEnrolSceneById(int id) throws Exception {

        EnrolScene enrolScene = enrolSceneMapper.selectByPrimaryKey(id);
        if (enrolScene == null || enrolScene.getState() == Constants.DEL_STATE) {
            throw new RuntimeException("场次信息有误");
        }

        City city = cityMapper.selectByPrimaryKey(enrolScene.getCityId());
        if (city == null || city.getState() == Constants.DEL_STATE) {
            throw new RuntimeException("城市信息有误");
        }
        enrolScene.setCity(city);
        EvaluationDate date = evaluationDateMapper.selectByPrimaryKey(enrolScene.getDateId());
        if (date == null || date.getState() == Constants.DEL_STATE) {
            throw new RuntimeException("时间信息有误");
        }
        enrolScene.setEvaluationDate(date);

        return enrolScene;
    }

    @Override
    public EnrolScene findEnrolSceneBySceneName(String sceneName) {

        EnrolSceneExample example = new EnrolSceneExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        criteria.andSceneEqualTo(sceneName);
        List<EnrolScene> enrolScenes = enrolSceneMapper.selectByExample(example);
        if (enrolScenes != null && enrolScenes.size() == 1) {
            return enrolScenes.get(0);
        }
        throw new RuntimeException("考场信息重复");
    }

    @Override
    public EnrolScene findEnrolSceneByDateId(Integer dateId) {
        EnrolScene scene = enrolSceneMapper.findEnrolSceneByDateId(dateId);
        if (scene == null) {
            throw new RuntimeException("找不到此时间点对应的场次信息");
        }
        return scene;
    }

    @Override
    public List<EnrolScene> findSceneBytestDate(String testDate) {

        List<EnrolScene> enrolScenes = enrolSceneMapper.findSceneBytestDate(testDate);

        return enrolScenes;
    }

    @Override
    public List<City> findEnrolSceneByCity(String testDate) {

        List<City> citys = enrolSceneMapper.findEnrolSceneByCity(testDate);

        return citys;
    }

    @Override
    public List<EnrolScene> findEnrolSceneByCityAndTestDate(String testDate, Integer cityId) {

        List<EnrolScene> enrolScenes = enrolSceneMapper.findEnrolSceneByCityAndTestDate(testDate, cityId);

        return enrolScenes;
    }

}
