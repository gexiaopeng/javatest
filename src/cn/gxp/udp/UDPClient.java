package cn.gxp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: UDPClient.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2015-1-30 上午10:16:55
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-1-30 上午10:16:55 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class UDPClient {
	public static void main(String[] args){
		DatagramSocket client=null;
		try {
			int timeout=20000;
			int port = 2000;
			String ip="127.0.0.1";//127.0.0.1
			//ip="61.155.116.145";
			client = new DatagramSocket();
			client.setSoTimeout(timeout);
			String sendStr = "Hello! I'm Client";
			byte[] sendBuf= sendStr.getBytes();
			InetAddress addr = InetAddress.getByName(ip);

			DatagramPacket sendPacket = new DatagramPacket(sendBuf ,sendBuf.length , addr , port);
			System.out.println("Client send...." );
			client.send(sendPacket);
			byte[] recvBuf = new byte[1024];
			DatagramPacket recvPacket= new DatagramPacket(recvBuf , recvBuf.length);
			System.out.println("Client receive...." );
			client.receive(recvPacket);
			String recvStr = new String(recvPacket.getData() , 0 ,recvPacket.getLength());
			System.out.println("Client 收到:" + recvStr);
		}catch (IOException e) {
			System.err.println("IOException! errmsg:"+e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(client!=null){
			client.close();
			System.out.println("client closed");
		}

	}
}
