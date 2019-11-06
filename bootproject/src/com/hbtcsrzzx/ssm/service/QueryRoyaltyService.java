package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;

import java.util.List;

public interface QueryRoyaltyService {
    PageInfo<Royalty> findRoyalty(Integer page,Integer limit,Integer id);
}
