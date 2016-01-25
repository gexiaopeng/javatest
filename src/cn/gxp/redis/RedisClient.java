package cn.gxp.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Jedis;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: RedisClient.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2015-11-16 下午4:56:52
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-11-16 下午4:56:52 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class RedisClient   
{  
	/** 
	 *  保存数据   类型为 Map 
	 * <一句话功能简述> 
	 * <功能详细描述> 
	 * @param flag 
	 * @param mapData 
	 * @see [类、类#方法、类#成员] 
	 */  
	public static void setMapDataToRedis(String flag,Map<String,String> mapData)  
	{         
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource();  
			redisClient.hmset(flag,mapData);  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}  
	}  

	/** 
	 *  保存数据   类型为 key-value 
	 * <一句话功能简述> 
	 * <功能详细描述> 
	 * @param flag 
	 * @param field 
	 * @param value 
	 * @see [类、类#方法、类#成员] 
	 */  
	public static void setDataToRedis(String flag,String field,String value)  
	{  
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource();  
			redisClient.hset(flag, field, value);  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}  
	}  

	/** 
	 *  获取Map数据 
	 * <一句话功能简述> 
	 * <功能详细描述> 
	 * @param flag 
	 * @return 
	 * @see [类、类#方法、类#成员] 
	 */  
	public static Map<String,String> getMapData(String flag)  
	{  
		Map<String,String> dataMap = null;  

		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource();  
			dataMap = redisClient.hgetAll(flag);  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}  
		return dataMap;  
	}  

	public static long deleteData(String flag)  
	{  
		long result = 0;  
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource();  
			result = redisClient.del(flag);  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}  

		return result;  
	}  

	/** 
	 * 根据key和字段获取数据  
	 * <一句话功能简述> 
	 * <功能详细描述> 
	 * @param flag 
	 * @param field 
	 * @return 
	 * @see [类、类#方法、类#成员] 
	 */  
	public static String getData(String flag,String field)  
	{  
		String data = null;  
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource();  
			data = redisClient.hget(flag, field);  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}  

		return data;  
	}  





	public void testList()  
	{    
		Jedis redis = RedisClientPool.jedisPool.getResource();  
		//hset key field value将哈希表key中的域field的值设为value。   
		redis.hset("table", "field1", "value1");   
		redis.hset("table", "field2", "value2");   
		redis.hset("table", "field3", "value3");   
		//返回哈希表key中，一个或多个给定域的值。   
		List<String> list = redis.hmget("table","field1","field2","field3");   
		for(String tmp : list)  
		{   
			System.out.println(tmp);   
		}   
	}  

	public static void testMap()  
	{  
		//同时将多个field - value(域-值)对设置到哈希表key中。   
		Map<String,String> map = new ConcurrentHashMap<String,String>();  
		for (int i = 0;i < 10000;i++)  
		{  
			map.put("field"+i, "value"+i);   
		}  
		if (null != getData("table", "field1"))  
		{  
			deleteData("table");  
		}  
		//得到map下面的username的值   
		Map<String,String> maps = getMapData("table");  
		System.out.println(maps.size());  

		setMapDataToRedis("table",map);  

		//HGETALL key返回哈希表key中，所有的域和值。   
		maps = getMapData("table");   

		System.out.println(maps.size());  
	} 
	public static byte[] getBytes(Object obj){
		byte[] bs=null;
		ByteArrayOutputStream bos =  new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos =  new ObjectOutputStream(bos);
			oos.writeObject(obj);
			bs = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bs;
	}
	public static Object getObject(byte[] bs){
		Object ob=null;
		ByteArrayInputStream bis =  new ByteArrayInputStream(bs);
		try {
			ObjectInputStream inputStream =  new ObjectInputStream(bis);
			ob=inputStream.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ob;
	}
	public static void main(String[] args)  throws Exception  
	{           
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPool.jedisPool.getResource(); 
			Set myset=new HashSet();
			List list=new ArrayList();
			myset.add("00");
			myset.add("01");
			myset.add(null);
			myset.add(null);
			list.add("00");
			list.add("03");
			list.add(null);
			list.add(null);
			System.out.println("myset:"+myset+"");
			System.out.println("list:"+list+"");
			//System.out.println("redisClient:"+redisClient);
			String key="mylist";
			Set set=redisClient.smembers(key);
			redisClient.lpush("pp", "pstring");
			//redisClient.
			System.out.println("set:"+set+"");
			Iterator t1=set.iterator() ;   
			while(t1.hasNext()){   
				Object obj1=t1.next();   
				System.out.println(obj1);   
			}  
			System.out.println("obj1");  
			redisClient.set("key blank", "dsdfsdf");
			System.out.println("key blank:"+redisClient.get("key blank"));
			List li=new ArrayList();
			System.out.println(redisClient.set("lili".getBytes(), getBytes(li)));
			System.out.println(getObject(redisClient.get("lili".getBytes())));
			//System.out.println(redisClient.brpop(10000, "pp"));
			//System.out.println(redisClient.brpop(1, "pp")); 
			//System.out.println("kk:"+redisClient.incr("kk"));
			//System.out.println("kk:"+redisClient.incrBy("kk",9));
			//System.out.println("kk2:"+redisClient.get("kk"));
			//System.out.println("kkkh:"+redisClient.decr("kkkh"));
		}   
		catch (Exception e)  
		{  
			e.printStackTrace();
			// 销毁对象  
			RedisClientPool.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPool.jedisPool.returnResource(redisClient);  
		}     
	} 

}  
