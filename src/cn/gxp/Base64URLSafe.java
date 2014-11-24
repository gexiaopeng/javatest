package cn.gxp;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: Base64URLSafe.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-11-24 上午10:57:26
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-11-24 上午10:57:26 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class Base64URLSafe {
	/** 
	 * BASE64加密 
	 *  
	 * @param key 
	 * @return 
	 * @throws Exception 
	 */ 
	public static String encryptBASE64(String key) throws Exception { 
		if (key == null || key.length() < 1) { 
			return ""; 
		} 
		return Base64.encodeBase64URLSafeString(key.getBytes("utf-8")); 
	} 
	/** 
	 * BASE64解密 
	 *  
	 * @param key 
	 * @return 
	 * @throws Exception 
	 */ 
	public static String decryptBASE64(String key) throws Exception { 
		if (key == null || key.length() < 1) { 
			return ""; 
		} 
		return new String(Base64.decodeBase64(key),"utf-8");
	} 

	public static void main(String[] args) { 
		try {
			String key = "02060F080B0802040E0F0409090A050C";
			byte[] midbytes = AES2.hexStringToBytes(key);
			String str="?s=中国086adssad是落地计费螺丝钉李方军我俄日物品为&b=日tyutyutyu6511是登陆根据是落地wq03r&c=sd/d";
			//str="http://www.iciba.com/TFY";
			String en=Base64.encodeBase64URLSafeString(str.getBytes("utf-8"));
			System.out.println("密文e1：[" + en+"]"+en.length());
			//en="zvDdIYsKiYlCrM4Yp0TFKi8-XdzRb64RhT-7pSgPWF74WQTaKqmAwQIsx3NWLnLx";
			String de=new String(Base64.decodeBase64(en),"utf-8");
			System.out.println("密文en：[" + en+"]"+en.length());
			System.out.println("原文s1：[" + str+"]"+str.getBytes("gbk").length);
			System.out.println("原文de：[" + de+"]");
			byte[] eee=AES2.encrypt(str.getBytes("gbk"), midbytes);
			//String en2=Base64.encodeBase64URLSafeString(eee);
			String en2=new String(Base64.encodeBase64(eee));
			System.out.println("en2：[" + en2+"]"+en2.length());
			String de2=new String(AES2.decryptToBytes(eee, midbytes),"gbk");
			System.out.println("de2：[" + de2+"]"+de2.getBytes("gbk").length);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
