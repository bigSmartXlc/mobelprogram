package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Content;

import java.util.List;

public interface ContentService {


    void addContent(Content content);

    PageInfo<Content> findAllContent(Integer page, Integer limit);

    void updateContent(Content content);

    Content findContentById(Long id);

    List<Content> findContentByCitegoryId(Integer citegoryId);
}
