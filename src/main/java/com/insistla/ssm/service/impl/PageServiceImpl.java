package com.insistla.ssm.service.impl;

import com.insistla.ssm.dao.PageDao;
import com.insistla.ssm.pojo.Page;
import com.insistla.ssm.service.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PageServiceImpl implements PageService {

    @Resource
    private PageDao pageDao;


    @Override
    public List<Page> selectPage() {
        return pageDao.selectPage();
    }

    @Override
    public Page selectPageById(Page page) {
        return pageDao.selectPageById(page);
    }
}
