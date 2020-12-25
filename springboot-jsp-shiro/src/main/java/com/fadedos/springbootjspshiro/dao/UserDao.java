package com.fadedos.springbootjspshiro.dao;

import com.fadedos.springbootjspshiro.entity.Perms;
import com.fadedos.springbootjspshiro.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Repository
public interface UserDao {
    void save(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询所有角色(User类属性 有角色集合)
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
