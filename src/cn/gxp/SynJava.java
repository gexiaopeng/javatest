package cn.gxp;
/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: SynJava.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2013-12-16 下午3:58:53
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2013-12-16 下午3:58:53 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class SynJava {
	Object objC=new Object();
	Object objD=new Object();
	public synchronized void synA(){
		
	}
	public synchronized void synB(){
	}
	
	public  void synC(){
		synchronized(objC){
		}
	}
	public void synD(){
		synchronized(objD){
		}
	}
}
