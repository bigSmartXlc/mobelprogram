package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Distribution;

public interface DistributionService {

    PageInfo<Distribution> findAllDistribution(Integer page, Integer limit);

    void addDistribution(Distribution distribution);

    void updateDistribution(Distribution distribution);

    Distribution findDistributionById(Integer id);
}
