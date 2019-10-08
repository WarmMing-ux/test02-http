package com.bdqn.test;

import com.bdqn.utils.JedisPoolUtils;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestJedis {


    /**
     * 1.2.10.3.1字符串类型string
     */
    @Test
    public void testString(){
        //获取jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //操作
        //存储
        String username = jedis.set("username","老何");
        //获取
        System.out.println("用户名"+username);
        //指定过期时间的key value
        jedis.setex("activeCode",20,"666666");
        System.out.println("激活码:"+jedis.get("activeCode"));
        //关闭连接
        jedis.close();
    }

    /**
     * 1.2.10.3.2哈希类型hash
     */
    @Test
    public void testHash(){
        //获取jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //操作
        //存储
        jedis.hset("user","name","张三");
        jedis.hset("user","age","20");
        //获取
        String name = jedis.hget("user","name");
        String age = jedis.hget("user","age");
        System.out.println("姓名"+name);
        System.out.println("年龄"+age);
        //获取所有map中的数据
        Map<String,String> map = jedis.hgetAll("user");
        //获取键的集合
        Set<String> keys = map.keySet();
        for (String key : keys) {
            //获取值
            String value = map.get(key);
            System.out.println(key+":"+value);
        }
        //关闭连接
        jedis.close();
    }

    /**
     * 1.2.10.3.3列表类型list
     */
    @Test
    public void testList(){
        //获取jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //操作
        //存储
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");
        //存储
        List<String> list = jedis.lrange("mylist",0,-1);
        System.out.println(list);
        String p1 = jedis.lpop("mylist");
        System.out.println(p1);
        String p2 = jedis.lpop("mylist");
        System.out.println(p2);
        //获取
        list = jedis.lrange("mylist",0,-1);
        System.out.println(list);
        //关闭连接
        jedis.close();
    }

    /**
     * 1.2.10.3.4集合类型set
     */
    @Test
    public void testSet(){
        //获取jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //操作
        //存储
        jedis.sadd("myset","Java","C#","spring","PHP");
        //获取
        Set<String> mySet = jedis.smembers("myset");
        System.out.println(mySet);
        //关闭连接
        jedis.close();
    }

    /**
     * 1.2.10.3.5有序集合类型sortedset
     */
    @Test
    public void testZSet(){
        //获取jedis对象
        Jedis jedis = new Jedis("localhost",6379);
        //操作
        //存储
        jedis.zadd("myZSet",1,"zhangsan");
        jedis.zadd("myZSet",2,"李四");
        jedis.zadd("myZSet",3,"王五");
        jedis.zadd("myZSet",4,"赵六");
        jedis.zadd("myZSet",5,"老何");
        //获取
        Set<String> mySet = jedis.zrange("myZSet",0,-1);
        System.out.println(mySet);
        //关闭连接
        jedis.close();
    }


    /**
     * Pool工具类的使用
     */
    @Test
    public void testPool(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("hello","hi");
        System.out.println("hello:"+jedis.get("hello"));
        //关闭连接
        jedis.close();
    }

}
