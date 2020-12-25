package com.fadedos.springbootjspshiro.service.impl;

import com.fadedos.springbootjspshiro.dao.UserDao;
import com.fadedos.springbootjspshiro.entity.Perms;
import com.fadedos.springbootjspshiro.entity.User;
import com.fadedos.springbootjspshiro.service.UserService;
import com.fadedos.springbootjspshiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void register(User user) {
        //处理业务调调用dao
        //明文密码进行MD5+salt
        //1生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setSalt(salt);
        //3.明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRolesByUsername(String username) {
        return userDao.findRolesByUsername(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(int id) {
        return userDao.findPermsByRoleId(id);
    }
}
