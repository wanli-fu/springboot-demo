package com.fadedos.springbootredis;

import com.fadedos.springbootredis.entity.User;
import com.fadedos.springbootredis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/31
 */
@SpringBootTest(classes = SpringbootRedisApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void testAll(){
        userService.findAll().forEach(user-> System.out.println("user = " + user));
        System.out.println("==========================");

        userService.findAll().forEach(user-> System.out.println("user = " + user));
    }
    @Test
    public void testById(){
        System.out.println("userService.findById(1) = " + userService.findById(1));
        System.out.println("==========================");

        System.out.println("userService.findById(1) = " + userService.findById(1));
    }

    @Test
    public void testDelete(){
        userService.delete(1);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("十一快乐").setPassword("123").setSalt("32313");
        userService.save(user);
    }
}
