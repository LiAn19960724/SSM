package com.insistla.ssm.service.impl;

import com.insistla.ssm.dao.UserDao;
import com.insistla.ssm.pojo.User;
import com.insistla.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;  //J2EE注解注入UserDao的接口


    @Override
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    @Override
    public User selectUserById(User user) {
        return userDao.selectUserById(user);
    }

    @Override
    public User selectUserByName(String userName) {
        return userDao.selectUserByName(userName);
    }
}
