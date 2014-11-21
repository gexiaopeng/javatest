package cn.gxp;
/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: String64.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-11-21 下午4:15:11
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-11-21 下午4:15:11 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class String64 {
	private static String base="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";

	public static char[] encode64Digit(byte[] bytes) {
		char[] digits64=base.toCharArray();
		char[] out = new char[bytes.length << 1];
		for (int i = 0, j = 0; i < bytes.length; i++) {
			out[j++] = digits64[(0xC0 & bytes[i]) >>> 6];
			out[j++] = digits64[0x3F & bytes[i]];
		}
		return out;
	}
	public static byte[] decode64Digit(char[] chars) {
		if ((0x01 & chars.length) != 0) {
			return null;
		}
		byte[] out = new byte[chars.length >> 1];
		for (int i = 0, j = 0; j < chars.length; i++) {
			int a = (getSize(chars[j]) << 6);
			j++;
			int b = getSize(chars[j]) & 0xFF;
			a = (b | getSize(chars[j]));
			j++;
			out[i] = (byte) (a & 0xFF);
		}
		return out;
	}
	public static int  getSize(char c){
		return (int)c;
	}
	public static void main(String[] args) {
		String s="nihao sdf年后";
		char c='u';
		char[] fv=encode64Digit(s.getBytes());
		System.out.println(new String(decode64Digit(fv)));
	}
}
