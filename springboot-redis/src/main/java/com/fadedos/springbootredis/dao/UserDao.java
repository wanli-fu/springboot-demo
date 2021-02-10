package com.fadedos.springbootredis.dao;


import com.fadedos.springbootredis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Repository
public interface UserDao {
    List<User> findAll();

    User findById(int id);

    void delete(int id);

    void save(User user);

}
