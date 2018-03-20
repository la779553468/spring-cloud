package com.jwebidai.util;

import com.jwebidai.SmsApplication;
import com.jwebidai.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static JedisPool pool = null;  
  
    private static RedisUtil ru = new RedisUtil();

    private RedisConfig redisConfig = (RedisConfig) SmsApplication.ac.getBean("redisConfig");
    
    private ThreadLocal<Jedis> threadLocalJedis = new ThreadLocal<Jedis>();

    public static RedisUtil use() {
    	return ru;
    }

    private RedisUtil() {
        if (pool == null) {  
            String ip = redisConfig.getIp();
            int port = redisConfig.getPort();
            int timeout = redisConfig.getTimeout();
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
            config.setMaxTotal(100);  
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
            config.setMaxIdle(10);  
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
            config.setMaxWaitMillis(1000 * 100);  
            config.setTestOnBorrow(true);  
            //使用默认db
            pool = new JedisPool(config, ip, port, timeout);
        }  
          
    }  
    
    public static void main(String[] args) {
		RedisUtil.use().set("zhangs", "nnihao");
		System.out.println(RedisUtil.use().get("zhangs"));
		RedisUtil.use().del("zhangs");
    }
  
    /** 
     * <p>通过key获取储存在redis中的value</p> 
     * <p>并释放连接</p> 
     * @param key 
     * @return 成功返回value 失败返回null 
     */  
    public String get(String key){  
        Jedis jedis = null;  
        String value = null;  
        try {  
        	jedis = getJedis();
            value = jedis.get(key);  
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {  
            jedis.close();
        }  
        return value;  
    }  
  
    /** 
     * <p>向redis存入key和value,并释放连接资源</p> 
     * <p>如果key已经存在 则覆盖</p> 
     * @param key 
     * @param value 
     * @return 成功 返回OK 失败返回 0 
     */  
    public String set(String key,String value){  
        Jedis jedis = null;  
        try {  
        	jedis = getJedis();
            return jedis.set(key, value);  
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";  
        } finally {  
        	jedis.close();
        }  
    }  
    
    /** 
     * 删除keys对应的记录,可以是多个key 
     * @return 删除的记录数
     * */  
    public long del(String... keys) {  
        Jedis jedis = getJedis();  
        long count = jedis.del(keys);  
        jedis.close();  
        return count;  
    }  
    
    public Jedis getJedis() {
    	Jedis jedis = threadLocalJedis.get();
		return jedis != null ? jedis : pool.getResource();
    }

    public String setExpireSeconds(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key,expire,value);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";
        } finally {
            jedis.close();
        }
    }

    public String setExpireMinute(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key,expire*60,value);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "0";
        } finally {
            jedis.close();
        }
    }
}