package com.hbtcsrzzx.ssm.service;

import com.hbtcsrzzx.ssm.po.ExamineePayLog;

public interface ExamineePayLogService {
    
    ExamineePayLog findExamineePayLogByOrderId(Long orderId);

    void updateExamineePayLog(ExamineePayLog examineePayLog);

    void insertExamineePayLog(ExamineePayLog examineePayLog);
}
