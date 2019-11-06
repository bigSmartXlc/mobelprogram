package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.DistributionMapper;
import com.hbtcsrzzx.ssm.po.Distribution;
import com.hbtcsrzzx.ssm.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {
    @Autowired
    private DistributionMapper distributionMapper;


    @Override
    public PageInfo<Distribution> findAllDistribution(Integer page, Integer limit) {

        if (page <= 0 || page == null) {

            page = 1;
        }

        if (limit <= 0 || limit == null) {

            limit = 10;
        }

        PageHelper.startPage(page, limit);

        List<Distribution> distributions = distributionMapper.findAllDistribution();
        PageInfo<Distribution> pageInfo = new PageInfo<>(distributions);
        return pageInfo;
    }

    @Override
    public void addDistribution(Distribution distribution) {

        distribution.setCreateTime(new Date());
        distribution.setUpdateTime(new Date());
        int tmp = distributionMapper.insert(distribution);
        if (tmp != 1) {
            throw new RuntimeException("添加失败");
        }

    }

    @Override
    public void updateDistribution(Distribution distribution) {

        distribution.setUpdateTime(new Date());
        int tmp =   distributionMapper.updateByPrimaryKeySelective(distribution);
        if (tmp != 1) {
            throw new RuntimeException("修改失败");
        }


    }

    @Override
    public Distribution findDistributionById(Integer id) {

        Distribution distribution = distributionMapper.findDistributionById(id);
        if(distribution == null){
            throw new RuntimeException("查询结果为空");
        }

        return distribution;
    }
}
