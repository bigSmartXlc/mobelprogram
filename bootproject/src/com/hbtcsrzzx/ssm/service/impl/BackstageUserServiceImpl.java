package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.BackstageUserMapper;
import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.ssm.service.BackstageUserService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BackstageUserServiceImpl implements BackstageUserService {


    @Autowired
    private BackstageUserMapper backstageUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<BackstageUser> findAllBackstageUser(Integer page, Integer limit) {

        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            page = 10;
        }


        Integer startPage = (page - 1) * limit;

        List<BackstageUser> BackstageUsers = backstageUserMapper.findAllBackstageUser(startPage, limit);
        if (BackstageUsers == null || BackstageUsers.size() <= 0) {
            throw new RuntimeException("查询结果为空");
        }

        return BackstageUsers;
    }

    @Override
    public int getCount() {

        Integer count = backstageUserMapper.getCount(Constants.DEL_STATE);
        if (count == null || count <= 0) {
            throw new RuntimeException("结果总数不正确");
        }

        return count;
    }

    @Override
    public BackstageUser findBackstageUserById(Integer id) {
        BackstageUser backstageUser = backstageUserMapper.findBackstageUserById(id);
        if (backstageUser == null) {
            throw new RuntimeException("找不到对应的角色信息");
        }

        return backstageUser;
    }

    @Override
    public int saveUserAndRole(Integer userId, Integer[] multi) {

        //先删除,在新增
        int delResult = backstageUserMapper.delUserAndRoleByUserId(userId);
        if (delResult < 0) {
            throw new RuntimeException("新增时,清除原有内容有误");
        }

        if(multi ==null || multi.length<=0){
            return delResult;
        }
        for (Integer roleId : multi) {
            Date date = new Date();

            int insertResult = backstageUserMapper.saveUserAndRoleByRoleId(userId, roleId, date, Constants.NORMAL_STATE);
            if (insertResult != 1) {
                throw new RuntimeException("新增角色出错");
            }
        }
        return 1;
    }

    @Override
    public BackstageUser findBackstageUserByUserName(Integer state, String username) {

        BackstageUser backstageUser = backstageUserMapper.findBackstageUserByUserName(state, username);
        if (backstageUser == null) {
            throw new RuntimeException("找不到对应的角色信息");
        }

        return backstageUser;
    }

    @Override
    public PageInfo<BackstageUser> findBackstageUsersByRoleName(String roleName, Integer page, Integer limit) {
        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            limit = 10;
        }
        //传入当前页码和每页显示数量
        PageHelper.startPage(page, limit);
        //查询数据库
        List<BackstageUser> backstageUsers = backstageUserMapper.findBackstageUsersByRoleName(Constants.DEL_STATE, roleName);
        //aop代理拼接
        PageInfo<BackstageUser> backstageUsersPageInfo = new PageInfo<>(backstageUsers);
        return backstageUsersPageInfo;
    }

    @Override
    public List<BackstageUser> findBackstageUserAllByInstitution() {

        return backstageUserMapper.findBackstageUserAllByInstitution();
    }


    @Override
    public int addBackstageUser(BackstageUser backstageUser) {
        backstageUser.setState(Constants.NORMAL_STATE);
        backstageUser.setCreatetime(new Date());
        backstageUser.setUpdatetime(new Date());
        //加密密码
        backstageUser.setPassword(bCryptPasswordEncoder.encode(backstageUser.getPassword()));
        int i = backstageUserMapper.insert(backstageUser);
        if (i != 1) {
            throw new RuntimeException("新增失败");
        }

        return i;
    }

    @Override
    public int updateBackstageUser(BackstageUser backstageUser) {
        //加密密码
        if (StringUtils.isNotEmpty(backstageUser.getPassword())) {
            backstageUser.setPassword(bCryptPasswordEncoder.encode(backstageUser.getPassword()));
        }
        int i = backstageUserMapper.updateByPrimaryKeySelective(backstageUser);

        if (i != 1) {
            throw new RuntimeException("修改失败");
        }

        return i;
    }
}
