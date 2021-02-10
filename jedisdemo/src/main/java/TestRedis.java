/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/31
 */

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 测试redis连接
 */
public class TestRedis {
    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //选择使用一个库 默认使用0号库
        jedis.select(0);
        System.out.println(jedis.ping());

        //获取redis的所有key信息
        Set<String> keys = jedis.keys("*");
        keys.forEach(key-> System.out.println("key="+key));

        //操作库相关
        jedis.flushDB();//清空当前库
        jedis.flushAll();//清空所有库
        //释放资源
        jedis.close();
    }

    }