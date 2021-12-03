package com.jfeng.springboot.service.impl;

import com.jfeng.springboot.bean.User;
import com.jfeng.springboot.mapper.UserMapper;
import com.jfeng.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JFeng
 * @date 2021/11/23
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public int addUser(String username, String password, String email) {
         return userMapper.addUser(username, password, email);
    }

    @Override
    public User findPwd(String username, String email) {
        return userMapper.findPwd(username, email);
    }

    @Override
    public User loginGo(String username, String password) {
        return userMapper.loginGo(username, password);
    }

    @Override
    public int delUser(Integer id) {
        return userMapper.delUser(id);
    }

}
