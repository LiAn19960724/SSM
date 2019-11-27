package com.insistla.ssm.dao;

import com.insistla.ssm.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface UserDao {

    /**
     * 查询所有用户
     */
    public List<User> selectUser();

    /**
     * 根据ID查询用户
     */
    public User selectUserById(User user);

    /**
     * 根据用户名查询用户对象
     */
    public User selectUserByName(String userName);


}
