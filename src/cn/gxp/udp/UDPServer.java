package cn.gxp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: UDPServer.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2015-1-30 上午10:01:04
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-1-30 上午10:01:04 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class UDPServer {
	public static void main(String[] args){
		try {
			DatagramSocket  server = new DatagramSocket(5050);
			byte[] recvBuf = new byte[100];
			DatagramPacket recvPacket= new DatagramPacket(recvBuf , recvBuf.length);
			System.out.println("---recvPacket....");
			server.receive(recvPacket);
			System.out.println("---receive InetAddress:"+server.getInetAddress()+",LocalAddress:"+server.getLocalAddress().getHostAddress());
			String recvStr = new String(recvPacket.getData() , 0 , recvPacket.getLength());
			int port = recvPacket.getPort();
			InetAddress addr = recvPacket.getAddress();
			System.out.println("recv port:"+port+",ip:"+addr.toString()+"[" + recvStr+"]");
			String sendStr = "Hello ! I'm Server";
			byte[] sendBuf= sendStr.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendBuf , sendBuf.length , addr , port );
			server.send(sendPacket);
			
			server.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
