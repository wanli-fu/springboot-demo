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
public class TestString {
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

    //测试String
    @Test
    public void testString(){
        //set
        jedis.set("name", "fadedos");
        //get
        String name = jedis.get("name");
        System.out.println(name);

        //mset
        jedis.mset("content", "好人", "address", "西湖区");
        //mget
        List<String> mget = jedis.mget("name", "content", "address");
        mget.forEach(v-> System.out.println("v="+v));

        //getset
        String set = jedis.getSet("name", "小明");
        System.out.println(set);

    }
}
