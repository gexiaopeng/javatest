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
		DatagramSocket  server = null;
		try {
			int sport=5050;
			server=new DatagramSocket(sport); //实例化套间字，指定自己的port  
			server.setSoTimeout(2000);
			while(true){
				try {
					byte[] recvBuf = new byte[100];
					DatagramPacket recvPacket= new DatagramPacket(recvBuf , recvBuf.length);
					System.out.println("Server---recvPacket....");
					server.receive(recvPacket);
					System.out.println("Server---receive InetAddress:"+server.getInetAddress()+",LocalAddress:"+server.getLocalAddress().getHostAddress());
					String recvStr = new String(recvPacket.getData() , 0 , recvPacket.getLength());
					int port = recvPacket.getPort();
					InetAddress addr = recvPacket.getAddress();
					System.out.println("Server recv port:"+port+",ip:"+addr.toString()+"[" + recvStr+"]");
					String sendStr = "Hello ! I'm Server";
					byte[] sendBuf= sendStr.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendBuf , sendBuf.length , addr , port );
					server.send(sendPacket);
				} catch (IOException e ) {
					System.err.println("IOException! errmsg:"+e.getMessage());
				}
			}

		} catch (SocketException e) {  
			System.out.println("Cannot open port!");  
			System.exit(1);   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(server!=null){
			server.close();
			System.out.println("server closed");
		}
	}
}
