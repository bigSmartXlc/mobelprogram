package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.UserAndExaminee;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;

public interface UserAndExamineeService {

    void addUserAndExaminee(UserAndExaminee userAndExaminee);

    PageInfo<Royalty> queryUserrebate(Integer userId, Integer page, Integer limit);
}
