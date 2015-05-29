package hemu.ment.core.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.inject.Named;

/**
 * Created by muu on 2015/5/29.
 */
@Named
public class RedisManager {

//    private static final String ip = null;
//    private static final int port = 0;
//    private static final int maxActive = 0;
//    private static final int maxIdle = 0;
//    private static final int maxWait = 0;
//    private static final boolean testOnBorrow = false;
//    private static final boolean testOnReturn = false;
//
//    private static final JedisPoolConfig poolConfig;
//
//    private static final JedisPool pool;
//
//    static {
//        poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(maxActive);
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMaxWaitMillis(maxWait);
//        poolConfig.setTestOnBorrow(testOnBorrow);
//        poolConfig.setTestOnReturn(testOnReturn);
//        pool = new JedisPool(poolConfig, ip, port, 3000);
//    }
//
//    public void destroy() {
//        pool.destroy();
//    }
//
//    public Jedis execute() {
//        return pool.getResource();
//    }
//
//    public void returnResource(Jedis jedis) {
//        pool.returnResource(jedis);
//    }

}
