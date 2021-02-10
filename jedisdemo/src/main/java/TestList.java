import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/31
 */
public class TestList {
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

    @Test
    public void testList(){
        //lpush
        jedis.lpush("names1", "张三", "李四", "win7");

        //rpush
        jedis.rpush("names1", "xiaoming");

        List<String> names1 = jedis.lrange("names1", 0, -1);
        names1.forEach(name-> System.out.println("name = " + name));

        //lpop
        String names11 = jedis.lpop("names1");
        System.out.println(names11);
    }
}
