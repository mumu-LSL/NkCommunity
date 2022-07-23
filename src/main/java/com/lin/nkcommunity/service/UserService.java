package com.lin.nkcommunity.service;

import com.lin.nkcommunity.dao.UserMapper;
import com.lin.nkcommunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    UserMapper userMapper;
    public User findUserById(int id){
       return userMapper.selectById(id);
    }
}