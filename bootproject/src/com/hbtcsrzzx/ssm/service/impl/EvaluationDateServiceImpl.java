package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CategoryMapper;
import com.hbtcsrzzx.ssm.dao.mapper.EvaluationDateMapper;
import com.hbtcsrzzx.ssm.dao.mapper.LevelMapper;
import com.hbtcsrzzx.ssm.dao.mapper.SubjectMapper;
import com.hbtcsrzzx.ssm.po.Category;
import com.hbtcsrzzx.ssm.po.EvaluationDate;
import com.hbtcsrzzx.ssm.po.EvaluationDateExample;
import com.hbtcsrzzx.ssm.po.EvaluationDateExample.Criteria;
import com.hbtcsrzzx.ssm.po.Level;
import com.hbtcsrzzx.ssm.po.Subject;
import com.hbtcsrzzx.ssm.service.EvaluationDateService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class EvaluationDateServiceImpl implements EvaluationDateService {

    @Autowired
    private EvaluationDateMapper evaluationDateMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<EvaluationDate> findAllEvaluationDate(int currentPage, int pageSize) throws Exception {

        EvaluationDateExample example = new EvaluationDateExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        int startRow = (currentPage - 1) * pageSize;
        example.setStartRow(startRow);
        example.setPageSize(pageSize);

        List<EvaluationDate> evaDateList = evaluationDateMapper.selectByExample(example);

        return checkState(evaDateList);
    }

    @Override
    public int getCount() {

        int cnt = 0;
        EvaluationDateExample example = new EvaluationDateExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Constants.DEL_STATE);
        try {
            cnt = evaluationDateMapper.countByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    /**
     * 检测结果集中表示的其他表中的状态是否合法，保证数据的准确性
     *
     * @param evaDateList
     */
    private List<EvaluationDate> checkState(List<EvaluationDate> evaDateList) {

        for (EvaluationDate evaluationDate : evaDateList) {

            Level level = levelMapper.selectByPrimaryKey(evaluationDate.getLid());
            if (level != null && level.getState() != Constants.DEL_STATE) {
                evaluationDate.setLevel(level);
            } else {
                evaDateList.remove(evaluationDate);
                continue;
            }

            Subject subject = subjectMapper.selectByPrimaryKey(evaluationDate.getSid());
            if (subject != null && subject.getState() != Constants.DEL_STATE) {
                evaluationDate.setSubject(subject);
            } else {
                evaDateList.remove(evaluationDate);
                continue;
            }

            Category category = categoryMapper.selectByPrimaryKey(evaluationDate.getCid());
            if (category != null && category.getState() != Constants.DEL_STATE) {
                evaluationDate.setCategory(category);
            } else {
                evaDateList.remove(evaluationDate);
                continue;
            }
        }

        return evaDateList;

    }

    @Override
    public int addEvaluationDate(EvaluationDate evaluationDate) throws Exception {
    	
    	Integer id = evaluationDateMapper.findIsRepeat(evaluationDate);
    	if(id != null){
    		throw new RuntimeException("添加时间重复");
    	}
        int res = -1;
        if (evaluationDate != null)
            res = evaluationDateMapper.insert(evaluationDate);
        return res;
    }

    @Override
    public int updateEvaluationDateById(EvaluationDate evaluationDate) throws Exception {
    	Integer id = evaluationDateMapper.findIsRepeat(evaluationDate);
    	if(id != null){
    		throw new RuntimeException("修改时间重复");
    	}
        return evaluationDateMapper.updateByPrimaryKeySelective(evaluationDate);
    }

    @Override
    public EvaluationDate findEvaluationDateById(int id) throws Exception {
        EvaluationDate evaluationDate = evaluationDateMapper.selectByPrimaryKey(id);
        if (evaluationDate != null && evaluationDate.getState() != Constants.DEL_STATE)
            return evaluationDate;
        return null;
    }

    @Override
    public List<EvaluationDate> findEnrolDate(Integer lid, Integer sid, Integer cid) {
        List<EvaluationDate> evaluationDates = evaluationDateMapper.findEnrolDate(lid, sid, cid);
        if (evaluationDates == null || evaluationDates.size() <= 0) {
            throw new RuntimeException("找不到对应的时间");
        }
        return evaluationDates;
    }

    @Override
    public List<EvaluationDate> findTestDateGroupBy() {

        List<EvaluationDate> evaluationDates = evaluationDateMapper.findTestDateGroupBy();
        if (evaluationDates == null || evaluationDates.size() <= 0) {
            throw new RuntimeException("没有对应的时间信息");
        }
        return evaluationDates;
    }

    @Override
    public List<Level> findLevelByCidAndSid(Integer cid, Integer sid) {
        List<Level> levels = evaluationDateMapper.findLevelByCidAndSid(cid, sid);
        if (levels == null || levels.size() <= 0) {
            throw new RuntimeException("根据类别和科目得到的结果为空");
        }
        return levels;
    }


    @Override
    public List<Level> findEvaluationDateByLid(String cname, String sname) {

        Integer cid = categoryMapper.findCategoryBycname(cname);
        Integer sid = subjectMapper.findSubjectBysname(sname);
        List<Level> levels = evaluationDateMapper.findLevelByCidAndSid(cid, sid);
        if (levels == null || levels.size() <= 0) {
            throw new RuntimeException("根据类别和科目得到的结果为空");
        }
        return levels;
    }

    @Override
    public List<Category> findCategoryGroupBy() {

        List<Category> categories = evaluationDateMapper.findCategoryGroupBy();

        if (categories == null || categories.size() <= 0) {
            throw new RuntimeException("找不到分类信息");
        }
        return categories;
    }

    @Override
    public List<Subject> findSubjectByCname(String cname) {

        List<Subject> subjects = evaluationDateMapper.findSubjectByCname(cname);

        if (subjects == null || subjects.size() <= 0) {
            throw new RuntimeException("找不到分类信息");
        }
        return subjects;
    }

    @Override
    public List<EvaluationDate> findEvaluationDateByLnameAndCnameAndSname(String lname, String cname, String sname) {

        Integer lid = levelMapper.findLevelById(lname);
        Integer cid = categoryMapper.findCategoryBycname(cname);
        Integer sid = subjectMapper.findSubjectBysname(sname);
       List<EvaluationDate> evaluationDates = evaluationDateMapper.findEnrolDate(lid,sid,cid);

        if (evaluationDates == null || evaluationDates.size() <= 0) {
            throw new RuntimeException("找不到对应的时间");
        }
        return evaluationDates;
    }

	@Override
	public List<EvaluationDate> findEvaluationDateByLidAndCidAndSidAndTestDate(Integer lid, Integer sid, Integer cid,
			String testDate) {
		List<EvaluationDate> evaluationDates = evaluationDateMapper.findEvaluationDateByLidAndCidAndSidAndTestDate(lid, sid, cid, testDate);
        if (evaluationDates == null || evaluationDates.size() <= 0) {
            throw new RuntimeException("找不到对应的时间");
        }
        return evaluationDates;
	}

}
