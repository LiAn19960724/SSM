package com.insistla.ssm.dao;

import com.insistla.ssm.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    /**
     * 查询所有用户
     */
     List<User> selectUser();

    /**
     * 根据ID查询用户
     */
     User selectUserById(User user);

    /**
     * 根据用户名查询用户对象
     */
     User selectUserByName(String userName);


}
