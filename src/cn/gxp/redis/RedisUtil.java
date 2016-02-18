package cn.gxp.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: RedisUtil.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2016-2-18 上午10:57:17
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2016-2-18 上午10:57:17 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class RedisUtil {
	protected static Logger logger = Logger.getLogger(RedisUtil.class);

	//Redis服务器IP
	private static String ADDR_ARRAY = "192.168.10.211";

	//Redis的端口号
	private static int PORT = 6378;

	//访问密码
	//    private static String AUTH = FileUtil.getPropertyValue("/properties/redis.properties", "auth");

	//可用连接实例的最大数目，默认值为8；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1000;;

	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 2000;;

	//超时时间
	private static int TIMEOUT = 10000;

	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60*60;          //一小时
	public final static int EXRP_DAY = 60*60*24;        //一天
	public final static int EXRP_MONTH = 60*60*24*30;   //一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool(){
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT);
		} catch (Exception e) {
			logger.error("First create JedisPool error : "+e);
		}
	}


	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {  
			initialPool();
		}
	}


	/**
	 * 同步获取Jedis实例
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {  
		if (jedisPool == null) {  
			poolInit();
		}
		Jedis jedis = null;
		boolean broken=false;
		try {  
			if (jedisPool != null) {  
				jedis = jedisPool.getResource(); 
			}
		} catch (Exception e) {  
			broken=true;
			logger.error("Get jedis error : "+e);
		}finally{
			returnResource(jedis,broken);
		}
		return jedis;
	}  


	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis,boolean broken) {
		if (jedis != null && jedisPool !=null) {
			try {
				if(broken){
					jedisPool.returnBrokenResource(jedis);
				}else{
					jedisPool.returnResource(jedis);
				}
			} catch (Exception e) {

			}
		}
	}


	/**
	 * 设置 String
	 * @param key
	 * @param value
	 */
	public static void setString(String key ,String value){
		try {
			if(key!=null && value!=null){
				getJedis().set(key,value);
			}
		} catch (Exception e) {
			logger.error("Set key error : "+e);
		}
	}

	/**
	 * 设置 过期时间
	 * @param key
	 * @param seconds 以秒为单位
	 * @param value
	 */
	public static void setString(String key ,int seconds,String value){
		try {
			if(key!=null && value!=null){
				getJedis().setex(key, seconds, value);
			}
		} catch (Exception e) {
			logger.error("Set keyex error : "+e);
		}
	}

	/**
	 * 获取String值
	 * @param key
	 * @return value
	 */
	public static String getString(String key){
		if(getJedis() == null || !getJedis().exists(key)){
			return null;
		}
		return getJedis().get(key);
	}

}
