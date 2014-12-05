package cn.gxp;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: Base62.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-12-5 下午1:06:34
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-12-5 下午1:06:34 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class Base62 {
	private static char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();  
	private static byte[] decodes = new byte[256];  
	static {  
		for (int i = 0; i < encodes.length; i++) {  
			decodes[encodes[i]] = (byte) i;  
		}  
	} 
	public static String encodeBase62(byte[] data) {  
		StringBuffer sb = new StringBuffer(data.length * 2);  
		int pos = 0, val = 0;  
		for (int i = 0; i < data.length; i++) {  
			val = (val << 8) | (data[i] & 0xFF);  
			pos += 8;  
			while (pos > 5) {  
				char c = encodes[val >> (pos -= 6)];  
				sb.append(  
						/**/c == 'i' ? "ia" :  
							/**/c == '+' ? "ib" :  
								/**/c == '/' ? "ic" : c);  
				val &= ((1 << pos) - 1);  
			}  
		}  
		if (pos > 0) {  
			char c = encodes[val << (6 - pos)];  
			sb.append(  
					/**/c == 'i' ? "ia" :  
						/**/c == '+' ? "ib" :  
							/**/c == '/' ? "ic" : c);  
		}  
		return sb.toString();  
	}  

	public static byte[] decodeBase62(String str) {  
		char[] data=str.toCharArray();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);  
		int pos = 0, val = 0;  
		for (int i = 0; i < data.length; i++) {  
			char c = data[i];  
			if (c == 'i') {  
				c = data[++i];  
				c =  
						/**/c == 'a' ? 'i' :  
							/**/c == 'b' ? '+' :  
								/**/c == 'c' ? '/' : data[--i];  
			}  
			val = (val << 6) | decodes[c];  
			pos += 6;  
			while (pos > 7) {  
				baos.write(val >> (pos -= 8));  
				val &= ((1 << pos) - 1);  
			}  
		}  
		return baos.toByteArray();  
	} 
	public static void main(String[] args) {
		String str="23435skhwle你好？=-2354&%*#@sldjfw3op-32我排位倒扣分";
		String c="utf-8";
		c="gbk";
		try {
			
			byte[] b=str.getBytes(c);
			 String es=encodeBase62(b);
			 //es="x55hGPZOrVhiaicuy2fT7iaiaqZQEUP8qlich4mky69umTuGBb1sbAh1Y3Ec124FrkZf4m0IvSIZmT9KUgoUaLV1jGHPZO7qc7VBu";
			 System.out.println("es:["+es+"]"+es.getBytes().length);
			 
			 System.out.println("1:["+str+"]");
			 byte[] bb=decodeBase62(es);
			  
			// bb=Base64.decodeBase64(bb);
			 System.out.println("2:["+new String(bb,c)+"]"+bb.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
