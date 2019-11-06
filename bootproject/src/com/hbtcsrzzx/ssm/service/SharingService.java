package com.hbtcsrzzx.ssm.service;

import com.hbtcsrzzx.ssm.po.Sharing;

import java.util.List;

public interface SharingService {
    //查
    List<Sharing> findAllSharing(Integer page, Integer limit);

    //查
    Object getCount();

    //查询指定纪录,用于删除
    Sharing findSharingById(Integer id);

    //新增
    int addSharing(Sharing sharing);

    //修改
    int updateSharing(Sharing sharing);

    /**
     * 根据机构性质查询业务员的提成比例
     * @param institutionalNature :机构性质
     * @return  业务员提成比例
     */
    int findalesmanScommissionByInstitutionalNature(String institutionalNature);
}
