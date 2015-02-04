package cn.gxp;


import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: AES.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-4-16 下午5:17:48
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-4-16 下午5:17:48 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class AES {
	/**
	 * 加密(代替des)
	 * 
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return
	 * @throws Exception 
	 */ 
	public static byte[] encrypt(String content, String password) throws Exception { 

		KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
		kgen.init(128, new SecureRandom(password.getBytes())); 
		SecretKey secretKey = kgen.generateKey(); 
		byte[] enCodeFormat = secretKey.getEncoded(); 
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES"); 
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器 
		byte[] byteContent = content.getBytes("utf-8"); 
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
		byte[] result = cipher.doFinal(byteContent); 
		return result; // 加密 
	} 
	/**解密
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 * @throws Exception 
	 */ 
	public static byte[] decrypt(byte[] content, String password) throws Exception { 
		KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
		kgen.init(128, new SecureRandom(password.getBytes())); 
		SecretKey secretKey = kgen.generateKey(); 
		byte[] enCodeFormat = secretKey.getEncoded(); 
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");             
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器 
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化 
		byte[] result = cipher.doFinal(content); 
		return result; // 加密 
	} 
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */ 
	public static byte[] parseHexStr2Byte(String hexStr) { 
		if (hexStr.length() < 1) 
			return null; 
		byte[] result = new byte[hexStr.length()/2]; 
		for (int i = 0;i< hexStr.length()/2; i++) { 
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16); 
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16); 
			result[i] = (byte) (high * 16 + low); 
		} 
		return result; 
	} 
	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */ 
	public static String parseByte2HexStr(byte buf[]) { 
		StringBuffer sb = new StringBuffer(); 
		for (int i = 0; i < buf.length; i++) { 
			String hex = Integer.toHexString(buf[i] & 0xFF); 
			if (hex.length() == 1) { 
				hex = '0' + hex; 
			} 
			sb.append(hex.toUpperCase()); 
		} 
		return sb.toString(); 
	} 
	public static void main(String[] args) {
		try {
			String content = "test111111111111erter我是登陆福建额外连接?=-2341"; 
			//content="1234567890";
			String password = "12"; 
			//password="02060F080B0802040E0F0409090A050C";
			//加密 
			System.out.println("加密前：" + content); 
			byte[] encryptResult = encrypt(content, password); 
			String encryptResultStr = Base64.encodeBase64URLSafeString(encryptResult); 
			//encryptResultStr=new String(Base64.encodeBase64(encryptResult));
			System.out.println("加密后：" + encryptResultStr+"|"+encryptResultStr.length()); 
			//解密 
			byte[] decryptResult = decrypt(Base64.decodeBase64(encryptResultStr),password);
			System.out.println("解密后：" + new String(decryptResult,"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
