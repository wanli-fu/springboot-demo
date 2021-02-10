package com.fadedos.springbootredis;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:spring data 为了方便我们对redis进行更友好的操作 因此有提供了bound api简化操作
 * @author: pengcheng
 * @date: 2020/12/31
 */
@SpringBootTest(classes = SpringbootRedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBoundAPI {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void testBound(){
        //redisTemplate stringRedisTemplate将一个key 多次操作进行绑定  对key绑定
        stringRedisTemplate.opsForValue().set("name","张三");
        stringRedisTemplate.opsForValue().append("name", "你是一个好人");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);

        //对字符串类型key进行绑定 后续所有操作都是基于这个key的操作
        BoundValueOperations<String, String> nameValueOperations = stringRedisTemplate.boundValueOps("name");
        nameValueOperations.set("李四");
        nameValueOperations.append("你是一个坏人");
        String s = nameValueOperations.get();
        System.out.println(s);


    }

}
