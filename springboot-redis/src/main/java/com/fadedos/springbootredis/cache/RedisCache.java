package com.fadedos.springbootredis.cache;

import com.fadedos.springbootredis.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

/**
 * @Description:自定义redis的缓存
 * @author: pengcheng
 * @date: 2020/12/31
 */
public class RedisCache implements Cache {
    private final String id;

    //必须存在构造方法
    //返回cache的唯一标识
    public RedisCache(String id) {
        System.out.println("id:==================>" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    //放入缓存值
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("key = " + key.toString());
        System.out.println("value = " + value.toString());
        //通过application工具类获取redisTemplate
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();

        //使用redis的hash类型 key hashkey value
        redisTemplate.opsForHash().put(id.toString(), getKeyToMd5(key.toString()), value);
    }

    //缓存中获取数据
    @Override
    public Object getObject(Object key) {
        System.out.println("key = " + key.toString());
        //根据key从redis中的hash类型中获取数据
        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
        return redisTemplate.opsForHash().get(getKeyToMd5(id.toString()), key.toString());
    }


    //这个方法不执行,默认没有实现,后续版本可能会实现
    @Override
    public Object removeObject(Object o) {
        System.out.println("根据指定的key删除缓存");
        return null;
    }

    //清空缓存
    @Override
    public void clear() {
        System.out.println("清空缓存~~~~") ;
        //清空namespace
        getRedisTemplate().delete(id.toString());//清空缓存
    }

    //用来计算缓存的数量
    @Override
    public int getSize() {
        //获取hash中key value的数量
        return getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }

    /**
     * 获取RedisTemplate,并设置 key的序列化方式
     * @return
     */
    private RedisTemplate<String, Object> getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 对key进行MD5的方法
     *
     */
    private  String getKeyToMd5(String Key){
        return DigestUtils.md5DigestAsHex(Key.getBytes());
    }
}
