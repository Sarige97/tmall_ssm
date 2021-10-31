package com.sarige.tmall.service;

import com.sarige.tmall.pojo.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void delete(int UserId);

    void update(User user);

    User get(int UserId);

    List<User> list();
}
