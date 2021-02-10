import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/31
 */

public class TestKey {
    private Jedis jedis;

    @Before
    public void before() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }

    @After
    public void after() {
        //释放资源
        jedis.close();
    }

    //测试key相关的key
    @Test
    public void testKey() {
        //删除一个key
        jedis.del("name");

        //删除多个key
        jedis.del("name", "age");

        //判断这个key释放存在
        Boolean name = jedis.exists("name");
        System.out.println(name);

        //设置一个key的超时时间
        Long age = jedis.expire("age", 100);
        System.out.println(age);

        //查看一个key的超时时间
        Long age1 = jedis.ttl("age");
        System.out.println(age1);

        //随机拿到一个key
        String s = jedis.randomKey();

        //修改key的名称
        jedis.rename("age", "newage");

        //查看key的对应值类型
        String name1 = jedis.type("name");
        System.out.println(name1);
    }

}
