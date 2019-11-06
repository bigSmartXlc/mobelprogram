package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.ssm.po.BackstageUserAndInstitution;
import com.hbtcsrzzx.ssm.po.Institution;
import com.hbtcsrzzx.ssm.po.InstitutionWithBLOBs;

public interface InstitutionService {
	public int addInstitution(InstitutionWithBLOBs institution) throws Exception;
	public InstitutionWithBLOBs findInstitutionById(int id) throws Exception;
	public int updateInstitutionById(InstitutionWithBLOBs institution) throws Exception;
	public int deleteInstitution(int id) throws Exception;
	public List<InstitutionWithBLOBs> findAllInstitution(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public int getCountByAddr(String city);
	public List<Institution> findByAddr(String city,int currentPage,int pageSize);
	/*按照姓名拼音排序*/
	public List<Institution> findAllInstitutionBSN(int currentPage,int pageSize)throws Exception;
	public List<InstitutionWithBLOBs> findAllInstitutionBasicInformation(Integer page, Integer limit)throws Exception;

	/**
	 * 保存机构关联的用户账号
	 * @backstageUserAndInstitution 机构id 与用户id
	 */
    void saveBackstageUserAndInstitution(BackstageUserAndInstitution backstageUserAndInstitution,String roleName);

	/**
	 * 根据机构id,查询用户列表
	 * @param institutionId  机构id
	 * @return 用户列表
	 */
	List<BackstageUser> findBackstageUserByInstitutionId(Integer institutionId);

    void delSalesmanUserByInstitutionId(Integer institutionId, Integer userId);

	List<Institution> findAllInstitutionOptimize(Integer page, Integer limit);
}
