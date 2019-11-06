package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import com.hbtcsrzzx.ssm.dao.mapper.BackstageUserAndInstitutionMapper;
import com.hbtcsrzzx.ssm.dao.mapper.BackstageUserMapper;
import com.hbtcsrzzx.ssm.po.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.InstitutionMapper;
import com.hbtcsrzzx.ssm.service.InstitutionService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionMapper institutionMapper;
    @Autowired
    private BackstageUserMapper backstageUserMapper;
    @Autowired
    private BackstageUserAndInstitutionMapper backstageUserAndInstitutionMapper;

    @Override
    public int addInstitution(InstitutionWithBLOBs institution) throws Exception {
        // TODO Auto-generated method stub
        int res = -1;
        if (institution != null)
            res = institutionMapper.insert(institution);
        return res;
    }

    @Override
    public InstitutionWithBLOBs findInstitutionById(int id) throws Exception {
        // TODO Auto-generated method stub
        InstitutionWithBLOBs institution = institutionMapper.selectByPrimaryKey(id);
        if (institution != null && institution.getState() != Constants.DEL_STATE) {
            return institution;
        }
        return null;
    }

    @Override
    public int updateInstitutionById(InstitutionWithBLOBs institution) throws Exception {
        int res = -1;
        if (institution != null)
            res = institutionMapper.updateByPrimaryKeyWithBLOBs(institution);
        return res;
    }

    @Override
    public int deleteInstitution(int id) throws Exception {
        // TODO Auto-generated method stub
        //int res = institutionMapper.deleteByPrimaryKey(id);
        return -1;//res;
    }

    @Override
    public List<InstitutionWithBLOBs> findAllInstitution(int currentPage, int pageSize) {
        InstitutionExample example = new InstitutionExample();
        InstitutionExample.Criteria criteria = example.createCriteria();
        int start = (currentPage - 1) * pageSize;
        example.setPageSize(pageSize);
        example.setStartRow(start);
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        List<InstitutionWithBLOBs> institutionList = institutionMapper.selectByExampleWithBLOBs(example);
        return institutionList;
    }

    @Override
    public int getCount(int state) {
        int cnt = 0;
        InstitutionExample example = new InstitutionExample();
        InstitutionExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(state);
        //criteria.andStateNotEqualTo(Constants.DEL_STATE);
        cnt = institutionMapper.countByExample(example);
        return cnt;
    }

    @Override
    public List<Institution> findByAddr(String city, int currentPage, int pageSize) {
        InstitutionExample example = new InstitutionExample();
        InstitutionExample.Criteria criteria = example.createCriteria();
        if (city != null && !city.equals("")) {
            criteria.andAddrLike("%" + city + "%");
        }
        int start = (currentPage - 1) * pageSize;
        example.setPageSize(pageSize);
        example.setStartRow(start);
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        List<Institution> institutionList = institutionMapper.selectByExample(example);
        return institutionList;
    }

    @Override
    public int getCountByAddr(String city) {
        InstitutionExample example = new InstitutionExample();
        InstitutionExample.Criteria criteria = example.createCriteria();
        if (city != null && !city.equals("")) {
            criteria.andAddrLike("%" + city + "%");
        }

        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        int cnt = institutionMapper.countByExample(example);
        return cnt;
    }

    @Override
    public List<Institution> findAllInstitutionBSN(int currentPage, int pageSize) {
        InstitutionExample example = new InstitutionExample();
        example.setOrderByClause("convert (`name` using gbk) ASC");
        InstitutionExample.Criteria criteria = example.createCriteria();
        int start = (currentPage - 1) * pageSize;
        example.setPageSize(pageSize);
        example.setStartRow(start);
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        List<Institution> instList = institutionMapper.selectByExample(example);
        return instList;
    }

    @Override
    public List<InstitutionWithBLOBs> findAllInstitutionBasicInformation(Integer page, Integer limit) throws Exception {

        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            limit = 10;
        }
        Integer start = (page - 1) * limit;

        List<InstitutionWithBLOBs> withBLOBs = institutionMapper.findAllInstitutionBasicInformation(start, limit);

        if (withBLOBs == null || withBLOBs.size() <= 0) {
            throw new RuntimeException("机构不存在");
        }

        return withBLOBs;
    }


    @Override
    public void saveBackstageUserAndInstitution(BackstageUserAndInstitution backstageUserAndInstitution, String roleName) {

        if (Constants.ROLE_NAME_INSTITUTION.equals(roleName)) {

            if (!isRoleNameInstitution(backstageUserAndInstitution.getBackstageUser())) {

                backstageUserMapper.addUserAndRoleByRoleName(backstageUserAndInstitution.getBackstageUser(), roleName, new Date(), new Date());
            }
        }

        //查询该角色是否是业务员
        if (Constants.ROLE_NAME_SALESMAN.equals(roleName)) {
            isRoleNameSalesman(backstageUserAndInstitution.getInstitutionId(),backstageUserAndInstitution.getBackstageUser());
        }


        //新增机构对应用户
        backstageUserAndInstitution.setState(0);
        backstageUserAndInstitution.setCreateTime(new Date());
        backstageUserAndInstitution.setUpdateTime(new Date());
        int i = backstageUserAndInstitutionMapper.insert(backstageUserAndInstitution);
        if (i != 1) {
            throw new RuntimeException("机构新增用户失败");
        }


    }


    @Override
    public List<BackstageUser> findBackstageUserByInstitutionId(Integer institutionId) {

        List<BackstageUser> backstageUsers = backstageUserAndInstitutionMapper.findBackstageUserByInstitutionId(institutionId);

        if (backstageUsers.size() > 2) {
            throw new RuntimeException("该机构绑定的用户列表个数超过2个");
        }

        return backstageUsers;
    }

    @Override
    public void delSalesmanUserByInstitutionId(Integer institutionId, Integer userId) {

        backstageUserAndInstitutionMapper.deleteUserAndInstitution(userId, institutionId);

    }

    @Override
    public List<Institution> findAllInstitutionOptimize(Integer currentPage, Integer pageSize) {

        InstitutionExample example = new InstitutionExample();
        InstitutionExample.Criteria criteria = example.createCriteria();
        int start = (currentPage - 1) * pageSize;
        example.setPageSize(pageSize);
        example.setStartRow(start);
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        List<Institution> instList = institutionMapper.selectByExample(example);
        return instList;
    }

    /**
     * 查询该机构的业务员用户是否存在,如果存在就删除
     * @param institutionId
     * @param userId
     */
    private void isRoleNameSalesman(Integer institutionId,Integer userId) {


        List<BackstageUser> backstageUserList = backstageUserMapper.findBackstageUserByinstitutionId(institutionId);
        if(backstageUserList.size()>2){
            throw new RuntimeException("该机构绑定的用户列表个数超过2个");
        }
        for (BackstageUser backstageUser : backstageUserList) {

            List<BackstageRole> backstageRoles = backstageUser.getBackstageRoles();
            for (BackstageRole backstageRole : backstageRoles) {
                if (Constants.ROLE_NAME_SALESMAN.equals(backstageRole.getRoleName())) {
                    //删除原有业务员
                    backstageUserAndInstitutionMapper.deleteUserAndInstitution(backstageUser.getId(), institutionId);
                }
            }
        }






    }

    /**
     * 查询该用户是否是机构用户
     * @param userId
     * @return
     */
    private boolean isRoleNameInstitution(Integer userId) {

        BackstageUser backstageUser = backstageUserMapper.findBackstageUserById(userId);

            List<BackstageRole> backstageRoles = backstageUser.getBackstageRoles();
            for (BackstageRole backstageRole : backstageRoles) {
                if (Constants.ROLE_NAME_INSTITUTION.equals(backstageRole.getRoleName())) {
                    return true;
                }
                if (Constants.ROLE_NAME_SALESMAN.equals(backstageRole.getRoleName())) {
                    throw new RuntimeException("添加机构时,指向的用户角色是业务员");
                }
            }


        return false;
    }
}