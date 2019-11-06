package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.UserAndExamineeMapper;
import com.hbtcsrzzx.ssm.po.UserAndExaminee;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import com.hbtcsrzzx.ssm.service.UserAndExamineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAndExamineeServiceImpl implements UserAndExamineeService {

    @Autowired
    private UserAndExamineeMapper userAndExamineeMapper;


    @Override
    public void addUserAndExaminee(UserAndExaminee userAndExaminee) {

        int i = userAndExamineeMapper.insert(userAndExaminee);
        if (i != 1) {
            throw new RuntimeException("新增失败");
        }
    }

    @Override
    public PageInfo<Royalty> queryUserrebate(Integer userId, Integer page, Integer limit) {

        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            limit = 10;
        }
        PageHelper.startPage(page,limit);
        List<Royalty> royalties = userAndExamineeMapper.queryUserrebate(userId);
        PageInfo<Royalty> pageInfo = new PageInfo<>(royalties);
        return pageInfo;
    }
}
