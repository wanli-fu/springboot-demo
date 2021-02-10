package com.fadedos.springbootredis.service;


import com.fadedos.springbootredis.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    void delete(int id);

    void save(User user);
}
