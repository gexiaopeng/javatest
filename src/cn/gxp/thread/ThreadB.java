package cn.gxp.thread;

import cn.gxp.SynJava;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: ThreadB.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2013-12-16 下午3:57:39
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2013-12-16 下午3:57:39 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class ThreadB extends Thread {
	private  SynJava  synJava;
	public ThreadB(SynJava  synJava){
		this.synJava=synJava;
	}
	public void run(){
		System.out.println("ThreadA run synB()....");
		synJava.synB();
		//synJava.synA();
	}
}
