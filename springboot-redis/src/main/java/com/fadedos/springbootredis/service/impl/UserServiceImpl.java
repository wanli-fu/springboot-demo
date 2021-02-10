package com.fadedos.springbootredis.service.impl;


import com.fadedos.springbootredis.dao.UserDao;
import com.fadedos.springbootredis.entity.User;
import com.fadedos.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
