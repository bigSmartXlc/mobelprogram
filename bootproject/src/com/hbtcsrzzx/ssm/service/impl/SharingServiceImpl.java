package com.hbtcsrzzx.ssm.service.impl;

import com.hbtcsrzzx.ssm.dao.mapper.SharingMapper;
import com.hbtcsrzzx.ssm.po.Sharing;
import com.hbtcsrzzx.ssm.service.SharingService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SharingServiceImpl implements SharingService {

    @Autowired
    private SharingMapper sharingMapper;

    @Override
    public List<Sharing> findAllSharing(Integer page, Integer limit) {

        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            page = 10;
        }


        Integer startPage = (page - 1) * limit;

        List<Sharing> sharings = sharingMapper.findAllSharing(startPage, limit);
        if (sharings == null || sharings.size() <= 0) {
            throw new RuntimeException("查询结果为空");
        }

        return sharings;
    }

    @Override
    public Object getCount() {

        Integer count = sharingMapper.getCount(Constants.DEL_STATE);
        if (count == null || count <= 0) {
            throw new RuntimeException("结果总数不正确");
        }

        return count;
    }

    @Override
    public Sharing findSharingById(Integer id) {
        Sharing sharing = sharingMapper.findSharingById(id);
        if (sharing == null) {
            throw new RuntimeException("找不到对应的分成纪录");
        }

        return sharing;
    }

    @Override
    public int addSharing(Sharing sharing) {

        sharing.setState(Constants.NORMAL_STATE);
        sharing.setCreateTime(new Date());
        sharing.setUpdateTime(new Date());
        int i = sharingMapper.insert(sharing);
        if (i != 1) {
            throw new RuntimeException("新增失败");
        }

        return i;
    }

    @Override
    public int updateSharing(Sharing sharing) {


        int i = sharingMapper.updateByPrimaryKeySelective(sharing);

        if (i != 1) {
            throw new RuntimeException("修改失败");
        }

        return i;
    }

    @Override
    public int findalesmanScommissionByInstitutionalNature(String institutionalNature) {

        int ratio = sharingMapper.findSalesmanCommissionByInstitutionalNature(institutionalNature);

        if(ratio<0 || ratio >=100){
            throw new RuntimeException("比例不正常");
        }

        return ratio;
    }
}
