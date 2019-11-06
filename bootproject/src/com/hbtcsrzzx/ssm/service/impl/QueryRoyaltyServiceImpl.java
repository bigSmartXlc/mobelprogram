package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.BackstageUserMapper;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import com.hbtcsrzzx.ssm.service.QueryRoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryRoyaltyServiceImpl implements QueryRoyaltyService {

    @Autowired
    private BackstageUserMapper backstageUserMapper;

    @Override
    public PageInfo<Royalty> findRoyalty(Integer page, Integer limit, Integer id) {
        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            limit = 10;
        }
        //查询该用户对应的角色
        BackstageUser backstageUser = backstageUserMapper.findBackstageUserById(id);

        //根据角色的不同  查询的sql也不同
        List<BackstageRole> roles = backstageUser.getBackstageRoles();
        int num = 0;
        List<Royalty> royalties = null;
        PageInfo<Royalty> royaltiePageInfo = null;
        for (BackstageRole role : roles) {

            if ("SALESMAN".equals(role.getRoleName())) {

                PageHelper.startPage(page, limit);
                royalties = backstageUserMapper.findSalesmanRoyalty(id);
                royaltiePageInfo = new PageInfo<>(royalties);
                num++;
            }

            if ("INSTITUTION".equals(role.getRoleName())) {
                PageHelper.startPage(page, limit);
                royalties = backstageUserMapper.findInstitutionRoyalty(id);
                royaltiePageInfo = new PageInfo<>(royalties);
                num++;
            }

        }

        if (num > 1) {
            throw new RuntimeException("该用户角色信息不合理,既有机构又有业务员");
        }
        return royaltiePageInfo;
    }
}
