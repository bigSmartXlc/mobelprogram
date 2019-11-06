package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CityMapper;
import com.hbtcsrzzx.ssm.dao.mapper.UserLogMapper;
import com.hbtcsrzzx.ssm.dao.mapper.UserLogRecordMapper;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.po.UserLogExample;
import com.hbtcsrzzx.ssm.po.UserLogExample.Criteria;
import com.hbtcsrzzx.ssm.po.UserLogRecord;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class UserLogServiceImpl implements UserLogService {

    @Autowired
    UserLogMapper userLogMapper;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    UserLogRecordMapper userLogRecordMapper;

    @Override
    public List<UserLog> findAllUserLog(int currentPage, int pageSize) {


        if (currentPage <= 0) {
            currentPage = 1;
        }

        if (pageSize <= 0) {
            pageSize = 1;
        }


        int startPage  =  (currentPage-1)*pageSize;

        List<UserLog> userLogs =  userLogMapper.findAllUserLog(startPage,pageSize);

        return  userLogs;

    }

    @Override
    public int getCount(int state) {
        UserLogExample example = new UserLogExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        return userLogMapper.countByExample(example);
    }

    @Override
    public int addUserLog(UserLog userLog) {
        int res = -1;

        if (userLog != null) {
            UserLog userRecommender = userLogMapper.findUserLogById(Integer.parseInt(userLog.getRecommender()));

            if(userRecommender == null){
                throw new RuntimeException("推荐人不存在");
            }
            //增加推荐人推荐时间
            userLog.setRecommendedTime(new Date());

            //增加默认分销等级
            userLog.setDistributionLevel(Constants.DISTRIBUTION_LEVEL_DEFAULT);
            // 加密密码
            userLog.setPassword(BCrypt.hashpw(userLog.getPassword(), BCrypt.gensalt()));
            userLog.setState(Constants.NORMAL_STATE);
            userLog.setCreationTime(new Date());
            userLog.setUpdateTime(new Date());
            res = userLogMapper.insert(userLog);
        }
        return res;
    }

    @Override
    public UserLog findUserLogById(int id) throws Exception {
        UserLog userLog = userLogMapper.selectByPrimaryKey(id);
        if (userLog != null && userLog.getState() != Constants.DEL_STATE) {
            return userLog;
        }
        return null;
    }

    @Override
    public int updateUserLogById(UserLog userLog) throws Exception {
        int res = -1;

        try {
            if (StringUtils.isNotEmpty(userLog.getPassword())) {
                // 加密密码
                userLog.setPassword(BCrypt.hashpw(userLog.getPassword(), BCrypt.gensalt()));
            }

            userLog.setPhone(null);
            userLog.setUpdateTime(new Date());
            res = userLogMapper.updateByPrimaryKeySelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public UserLog findUserLogByPhone(String userPhone) {
        UserLogExample example = new UserLogExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        criteria.andPhoneEqualTo(userPhone);
        List<UserLog> userLogs = userLogMapper.selectByExample(example);
        if (userLogs != null && userLogs.size() == 1) {
            UserLog userLog = userLogs.get(0);
            userLog.setCity(cityMapper.selectByPrimaryKey(userLog.getCityId()));
            return userLog;
        }
        return null;
    }

    @Override
    public boolean getIsUserLog(String phone, String password) {
        UserLog userLog = findUserLogByPhone(phone);
        if (userLog != null) {
            return BCrypt.checkpw(password, userLog.getPassword());
        }
        return false;
    }

    @Override
    public void addUserLogRecord(String ipAddress, String phone) {
        UserLogRecord logRecord = new UserLogRecord();
        logRecord.setCreateTime(new Date());
        logRecord.setIpAddress(ipAddress);
        logRecord.setUserPhone(phone);
        userLogRecordMapper.insert(logRecord);

    }

}
