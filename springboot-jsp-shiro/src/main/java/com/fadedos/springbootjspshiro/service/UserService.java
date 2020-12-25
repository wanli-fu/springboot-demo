package com.fadedos.springbootjspshiro.service;

import com.fadedos.springbootjspshiro.entity.Perms;
import com.fadedos.springbootjspshiro.entity.User;

import java.util.List;

public interface UserService {
    //注册方法
    void register(User user);

    User findByUsername(String username);

    /**
     * 根据用户名查询所有角色
     * @param username
     * @return
     */
    User findRolesByUsername(String username);

    /**
     * 根据角色id查询权限集合
     * @param id
     * @return
     */
    List<Perms> findPermsByRoleId(int id);
}
