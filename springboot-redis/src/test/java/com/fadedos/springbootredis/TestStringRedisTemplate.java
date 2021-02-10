package com.fadedos.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/31
 */
@SpringBootTest(classes = SpringbootRedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStringRedisTemplate {
    //注入stringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate; //key value都是字符串

    //操作key相关
    @Test
    public void testKey(){
        stringRedisTemplate.delete("name");//删除key

        Boolean name = stringRedisTemplate.hasKey("name");//判断某个key是否存在
        System.out.println(name);

        Set<String> keys = stringRedisTemplate.keys("*");//获取redis中的所有key
        keys.forEach(key-> System.out.println("key = " + key));

        Long name1 = stringRedisTemplate.getExpire("name");//获取key的超时时间 -1 永不超时  -2 key存在 >0 过期时间
        System.out.println("name1 = " + name1);

        String randomKey = stringRedisTemplate.randomKey();//在redis中随机获取key

        stringRedisTemplate.rename("age","age1");//修改key名字 要求key必须存在,不存在报错

        stringRedisTemplate.renameIfAbsent("age", "age1"); //oldkey不存在报错,当newkey已经存在也报错

        stringRedisTemplate.move("name", 1);//移动key到指定的库

    }

    //操作字符串 opsForValue 实际操作就是redis的String类型
    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("name", "张三");//set 设置key的value

        String name = stringRedisTemplate.opsForValue().get("name"); //get 获取key的value
        System.out.println(name);

        stringRedisTemplate.opsForValue().set("name", "李四", 120, TimeUnit.SECONDS);//设置key的超时时间

        stringRedisTemplate.opsForValue().append("name", "追加内容");//追加内容
    }

    //操作list opsForList 实际操作就是redis的List类型
    @Test
    public void testList(){
        stringRedisTemplate.opsForList().leftPush("names", "小陈"); //创建一个列表放入一个元素
        stringRedisTemplate.opsForList().leftPushAll("names", "张三", "李四", "王五"); //创建一个列表放入多个元素

        List<String> names = stringRedisTemplate.opsForList().range("names", 0, -1);
        names.forEach(name-> System.out.println("name = " + name));
    }


}


