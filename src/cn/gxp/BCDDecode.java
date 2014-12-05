package cn.gxp;
/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 将十进制的数字转化为二进制
 * <br>
 * File Name: BCDDecode.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-12-5 上午9:14:11
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-12-5 上午9:14:11 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class BCDDecode {
	/** 
	 * @功能:测试用例 
	 * @参数: 参数 
	 */  
	public static void main(String[] args) {  
		String str="56785674520107867967967967";
		byte[] b = str2Bcd(str);
		System.out.println("0["+new String(b)+"]");
		System.out.println("1["+str+"]"+str.getBytes().length); 
		System.out.println("2["+bcd2Str(b)+"]"+b.length);  
	}  

	/** 
	 * @功能: BCD码转为10进制串(阿拉伯数据) 
	 * @参数: BCD码 
	 * @结果: 10进制串 
	 */  
	public static String bcd2Str(byte[] bytes) {  
		StringBuffer temp = new StringBuffer(bytes.length * 2);  
		for (int i = 0; i < bytes.length; i++) {  
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));  
			temp.append((byte) (bytes[i] & 0x0f));  
		}  
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp  
				.toString().substring(1) : temp.toString();  
	}  

	/** 
	 * @功能: 10进制串转为BCD码 
	 * @参数: 10进制串 
	 * @结果: BCD码 
	 */  
	public static byte[] str2Bcd(String asc) {  
		int len = asc.length();  
		int mod = len % 2;  
		if (mod != 0) {  
			asc = "0" + asc;  
			len = asc.length();  
		}  
		byte abt[] = new byte[len];  
		if (len >= 2) {  
			len = len / 2;  
		}  
		byte bbt[] = new byte[len];  
		abt = asc.getBytes();  
		int j, k;  
		for (int p = 0; p < asc.length() / 2; p++) {  
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
				j = abt[2 * p] - '0';  
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
				j = abt[2 * p] - 'a' + 0x0a;  
			} else {  
				j = abt[2 * p] - 'A' + 0x0a;  
			}  
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
				k = abt[2 * p + 1] - '0';  
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
				k = abt[2 * p + 1] - 'a' + 0x0a;  
			} else {  
				k = abt[2 * p + 1] - 'A' + 0x0a;  
			}  
			int a = (j << 4) + k;  
			byte b = (byte) a;  
			bbt[p] = b;  
		}  
		return bbt;  
	}  
}
