package cn.gxp.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;




/** 
*  取redis 连接池 
* <一句话功能简述> 
* <功能详细描述> 
*  
* @author  khj 
* @see  [相关类/方法] 
* @since  [产品/模块版本] 
*/  
public class RedisClientPool  
{  
public static RedisClientPool redisClientPool = getInstance();  
     
   public static JedisPool jedisPool;  
     
   public static synchronized RedisClientPool getInstance()  
   {  
       if (null == redisClientPool)  
       {  
           redisClientPool = new RedisClientPool();  
       }  
       return redisClientPool;  
   }  
     
   public RedisClientPool()  
   {  
       if (null == jedisPool)  
       {  
           init();  
       }  
   }  
     
   /** 
    *   初始化Jedis 
    * <一句话功能简述> 
    * <功能详细描述> 
    * @return 
    * @see [类、类#方法、类#成员] 
    */  
   private static JedisPoolConfig initPoolConfig()  
   {  
       JedisPoolConfig jedisPoolConfig =new JedisPoolConfig(); 
       // 控制一个pool最多有多少个状态为idle的jedis实例  
       
       jedisPoolConfig.setMaxTotal(10000); 
       // 最大能够保持空闲状态的对象数  
       jedisPoolConfig.setMaxIdle(300);  
       // 超时时间  
       jedisPoolConfig.setMaxWaitMillis(1000);  
      // jedisPoolConfig.setMaxWait(1000);
       //jedisPoolConfig.setMaxWait(1000l);
       // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；  
       jedisPoolConfig.setTestOnBorrow(true);  
       // 在还会给pool时，是否提前进行validate操作  
       jedisPoolConfig.setTestOnReturn(true);  
         
       return jedisPoolConfig;  
   }  
     
   /** 
    * 初始化jedis连接池 
    */  
   public static void init()  
   {  
	   JedisPoolConfig jedisPoolConfig = initPoolConfig();  
       String host = "192.168.10.211";//"localhost";
       host="60.191.53.35";
       int port = 7001;//6379 7001; 
       int timeout = 100000;//60000;  
       // 构造连接池  
       jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout); 
       //jedisPool=new JedisPool(host, port);
   }  
}
