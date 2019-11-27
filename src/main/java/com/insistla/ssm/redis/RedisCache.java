package com.insistla.ssm.redis;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 缓存类
 */
@Repository("redisCache")
public class RedisCache {

    @Resource
    private JedisPool jedisPool;

    //设置数据到Redis(value为json格式的字符串)
    //注意：键key最好为方法的全路径名，对象或集合存入时转成json格式的字符串
    //取出的时候把json格式的字符串反转成对象或集合就OK了
    public void setDateToRedis(String key,String value){
        //先获得池中的jedis对象
        Jedis jedis = jedisPool.getResource();
        //再进行jedis操作
        jedis.set(key, value);
    }

    //获取Redis中的数据
    public String getDataFromRedis(String key){
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }


}
