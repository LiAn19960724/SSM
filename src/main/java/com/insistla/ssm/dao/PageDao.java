package com.insistla.ssm.dao;

import com.insistla.ssm.pojo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageDao {

    /**
     * 查询所有用户
     */
    public List<Page> selectPage();


    /**
     * 根据ID查询用户
     */
    public Page selectPageById(Page page);



}
