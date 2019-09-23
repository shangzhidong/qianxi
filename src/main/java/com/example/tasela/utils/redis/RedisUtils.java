package com.example.tasela.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @author jinmos
 * @date 2019-09-19 10:04
 * redis工具类
 */
@Component
public class RedisUtils {

    private Logger log= LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private JedisPool jedisPool;


    /**
     * 设置键值对
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str=null;
        try {
            str=jedis.set(key, value);
        } catch (Exception e) {
            log.error("设置键值对:"+e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return str;
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str=null;
        try {
            str=jedis.get(key);
        } catch (Exception e) {
            log.error("根据key获取值:"+e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return str;
    }


    /**
     * 向key后追加值
     * @param key
     * @param str
     * @return
     */
    public Long append(String key, String str) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.append(key, str);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }

    /**
     * 检验key是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    /**
     * * <p>
     *     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。	 *
     * </p>
     * *
     * * @param key	 * @param value	 *
     * 过期时间，单位：秒
     * * @return 成功返回1 如果存在 和 发生异常 返回 0
     * */
    public Long expire(String key, int value, int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.expire(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    /**
     * * <p>
     *     * 以秒为单位，返回给定 key 的剩余生存时间	 *
     * </p>	 *
     *
     * * @param key	 *
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。
     * 否则，以秒为单位，返回 key	 *         的剩余生存时间。 发生异常 返回 0
     * */
    public Long ttl(String key,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**	 * 返还到连接池
     * *
     * * @param jedisPool
     * * @param jedis
     * */
    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
        }
}
