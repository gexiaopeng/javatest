package cn.gxp;

import cn.gxp.thread.ThreadA;
import cn.gxp.thread.ThreadB;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: TestJava.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2013-12-16 下午3:56:17
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2013-12-16 下午3:56:17 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class TestJava {

	/**
	 * gxp 2013-12-16 下午3:56:17
	 * @param args
	 */
	public static void main(String[] args) {
	     //test
		synJava();
	}
	public static void synJava(){
		SynJava synJava=new SynJava();
		ThreadA threadA =new ThreadA(synJava);
		ThreadB threadB =new ThreadB(synJava);
		threadA.start();
		threadB.start();
	}

}
