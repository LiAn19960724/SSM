package com.insistla.ssm.dao;

import com.insistla.ssm.pojo.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageDao {

    /**
     * 查询所有用户
     */
     List<Page> selectPage();


    /**
     * 根据ID查询用户
     */
     Page selectPageById(Page page);



}
