package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.ContentMapper;
import com.hbtcsrzzx.ssm.po.Content;
import com.hbtcsrzzx.ssm.service.ContentService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void addContent(Content content) {
        content.setState(Constants.NORMAL_STATE);
        int tmp = contentMapper.insertSelective(content);
        if (tmp != 1) {
            throw new RuntimeException("新增失败");
        }

    }

    @Override
    public PageInfo<Content> findAllContent(Integer page, Integer limit) {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (limit == null || limit <= 0) {
            limit = 10;
        }
        int start = (page - 1) * limit;

        PageHelper.startPage(start, limit);
        List<Content> contents =  contentMapper.findAllContent(Constants.DEL_STATE);
        PageInfo<Content> contentPageInfo = new PageInfo<>(contents);

        if(contentPageInfo == null){
            throw new RuntimeException("查询出错");
        }

        return contentPageInfo;
    }

    @Override
    public void updateContent(Content content) {

        if(content.getId() <= 0){
            throw new RuntimeException("修改的id值非法");
        }
        int tmp = contentMapper.updateByPrimaryKeySelective(content);
        if (tmp != 1) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public Content findContentById(Long id) {

        Content content = contentMapper.selectByPrimaryKey(id);

        if(content == null || content.getState()== Constants.DEL_STATE){
            throw new RuntimeException("该对应不存在或者以删除");
        }
        return content;
    }

    @Override
    public List<Content> findContentByCitegoryId(Integer citegoryId) {


        return  contentMapper.findContentByCitegoryId(citegoryId,Constants.DEL_STATE);
    }
}
