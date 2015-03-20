/*
 * @(#)DataTypeChangeUtil.java 2015-2-1上午1:14:52
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package cn.gxp.udp.h3client;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据类型相互转换工具类
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2015-2-1上午1:14:52 TODO</li>
 * </ul> 
 */

public class DataTypeChangeUtil {


	public static void main(String[] args) {
		String a = "900150983CD24FB0D6963F7D28E17F72";
		byte[] hexStrBytes = DataTypeChangeUtil.hexStrToBytes(a);
		System.out.println(hexStrBytes.length);
		System.out.println(DataTypeChangeUtil.bytesToHexStr(hexStrBytes));
	}
	/**
	 * ip地址转byte
	 * @author liqg
	 * @creationDate. 2015-2-1 上午1:16:12 
	 * @param ipAddr
	 * @return
	 */
	public static byte[] ipToBytes(String ipAddr) {
		byte[] ret = new byte[4];
		try {
			String[] ipArr = ipAddr.split("\\.");
			ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
			return ret;
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}
	/**
	 * ip地址的byte类型转String
	 * @author liqg
	 * @creationDate. 2015-2-1 上午1:18:26 
	 * @param ipBytes
	 * @return
	 */
	public static String bytesToIp(byte[] ipBytes) {
		if (ipBytes.length == 4) {
			return new StringBuffer().append(ipBytes[0] & 0xFF).append('.').append(
					ipBytes[1] & 0xFF).append('.').append(ipBytes[2] & 0xFF)
					.append('.').append(ipBytes[3] & 0xFF).toString();
		} else {
			return null;
		}
	}
	/**
	 * short转byte[]
	 * @author liqg
	 * @creationDate. 2015-2-1 上午1:21:28 
	 * @param data
	 * @return
	 */
	public static byte[] shortToByte(short data) {
		byte[] shortBuf = new byte[2];
		shortBuf[0] = (byte) ((data >>> 8) & 0xFF);
		shortBuf[1] = (byte) (data & 0xFF);
		return shortBuf;
	}
	/**
	 * byte[]转short
	 * @author liqg
	 * @creationDate. 2015-2-2 下午5:13:25 
	 * @param shortBytes
	 * @return
	 */
	public static short byteToShort(byte[] shortBytes) {
		if (shortBytes.length == 2) {
			return (short) (shortBytes[0] << 8 | (shortBytes[1] & 0xff));
		} else {
			return 0;
		}
	}
	/**
	 * byte[]转16进制字符串
	 * @author liqg
	 * @creationDate. 2015-2-4 下午3:58:55 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexStr(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder("");
	    if (bytes == null || bytes.length <= 0) {
	        return null;
	    }
	    for (int i = 0; i < bytes.length; i++) {
	        int v = bytes[i] & 0xFF;
	        String hv = Integer.toHexString(v);
	        if (hv.length() < 2) {
	            stringBuilder.append(0);
	        }
	        stringBuilder.append(hv);
	    }
	    return stringBuilder.toString();
	}
	/**
	 * 16进制字符串转byte[]
	 * @author liqg
	 * @creationDate. 2015-2-4 下午3:57:08 
	 * @param hexStr
	 * @return
	 */
	public static byte[] hexStrToBytes(String hexStr) {
		if (StringUtils.isBlank(hexStr)) {
	        return null;
	    }
		hexStr = hexStr.toUpperCase();
	    int length = hexStr.length() / 2;
	    char[] hexChars = hexStr.toCharArray();
	    byte[] d = new byte[length];
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;
	}
	/**
	 * char转byte
	 * @author liqg
	 * @creationDate. 2015-2-4 下午3:56:29 
	 * @param c
	 * @return
	 */
	private static byte charToByte(char c) {
	    return (byte) "0123456789ABCDEF".indexOf(c);
	}

}
