package com.hbtcsrzzx.ssm.service.impl;

import com.hbtcsrzzx.ssm.dao.mapper.ExamineePayLogMapper;
import com.hbtcsrzzx.ssm.po.ExamineePayLog;
import com.hbtcsrzzx.ssm.service.ExamineePayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamineePayLogServiceImpl implements ExamineePayLogService {
    @Autowired
    private ExamineePayLogMapper examineePayLogMapper;

    @Override
    public ExamineePayLog findExamineePayLogByOrderId(Long oredrId) {

        ExamineePayLog examineePayLog = examineePayLogMapper.selectByPrimaryKey(oredrId);

        if (examineePayLog == null) {
            throw new RuntimeException("找不到该条支付日志记录");
        }
        return examineePayLog;
    }

    @Override
    public void updateExamineePayLog(ExamineePayLog examineePayLog) {

        int i = examineePayLogMapper.updateByPrimaryKeySelective(examineePayLog);
        if (i != 1) {
            throw new RuntimeException("修改支付日志出错");
        }

    }

    @Override
    public void insertExamineePayLog(ExamineePayLog examineePayLog) {

        int i = examineePayLogMapper.insertSelective(examineePayLog);
        if (i != 1) {
            throw new RuntimeException("支付日志信息新增失败");

        }
    }
}
