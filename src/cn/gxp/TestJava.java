package cn.gxp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


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
		try {
			base64();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void getHtml() throws Exception{
		String url="http://zhili.yofogo.com/";
		url="http://www.mof.gov.cn/zhengwuxinxi/bulinggonggao/tongzhitonggao/";
	   // url="http://item.taobao.com/item.htm?spm=a215f.6985601.1995840397.2.sjZu07";
		//url="http://www.weatao.com/";
		Document doc=null;
		doc=Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").header("Accept-Encoding", "").get();

		System.out.println(doc.html()); 
	}
	public static void getPageSource(){
		String url="http://zhili.yofogo.com/";
		//url="http://www.mof.gov.cn/zhengwuxinxi/bulinggonggao/tongzhitonggao/?jjj=2";
		//url="http://item.taobao.com/item.htm?spm=a215f.6985601.1995840397.2.sjZu07";
		//url="http://www.weatao.com";
		//url="http://www.weather.com.cn/photo/2013/12/gqt/2014066.shtml";
		url="http://js.iwififree.com/wifiPortal/index.jsp";
		url="http://vdisk.weibo.com/";
		WebDriver driver =new HtmlUnitDriver();
		driver.get(url);
		System.out.println("{"+driver.getTitle()+"}");
		String html = driver.getPageSource();
		System.out.println(html);
	}
	public static void synJava(){
		SynJava synJava=new SynJava();
		ThreadA threadA =new ThreadA(synJava);
		ThreadB threadB =new ThreadB(synJava);
		threadA.start();
		threadB.start();
	}
	public static void base64(){
		String s="中国086adssad是落地计费螺丝钉李方军我俄日物品为日tyutyutyu6511";
		//5Lit5Zu9MDg2YWRzc2Fk5piv6JC95Zyw6K6h6LS56J665Lid6ZKJ5p2O5pa55Yab5oiR5L+E5pel54mp5ZOB5Li65peldHl1dHl1dHl1NjUxMQ==
		//5Lit5Zu9MDg2YWRzc2Fk5piv6JC95Zyw6K6h6LS56J665Lid6ZKJ5p2O5pa55Yab5oiR5L+E5pel54mp5ZOB5Li65peldHl1dHl1dHl1NjUxMQAA
		//5Lit5Zu9MDg2YWRzc2Fk5piv6JC95Zyw6K6h6LS56J665Lid6ZKJ5p2O5pa55Yab5oiR5L+E5pel54mp5ZOB5Li65peldHl1dHl1dHl1NjUxMQ==
		//5Lit5Zu9MDg2YWRzc2Fk5piv6JC95Zyw6K6h6LS56J665Lid6ZKJ5p2O5pa55Yab5oiR5L-E5pel54mp5ZOB5Li65peldHl1dHl1dHl1NjUxMQ
		byte[] b=s.getBytes();
		String e = "Base64.e";
		String d = new String(Base64.decodeBase64(e));
		
		System.out.println("密文e：[" + e+"]"+e.length());
		System.out.println("原文s：[" + s+"]");
		System.out.println("原文d：[" + d+"]");
	}

}
