package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.UserMapper;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.UserService;
import com.sarige.tmall.util.example.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(int UserId) {
        userMapper.deleteByPrimaryKey(UserId);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(int UserId) {
        return userMapper.selectByPrimaryKey(UserId);
    }

    @Override
    public List<User> list() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("id desc");
        return userMapper.selectByExample(userExample);
    }
}
