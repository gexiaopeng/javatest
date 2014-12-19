package cn.gxp;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	private static HttpClient client;
	static {
		client=new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(30);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		client.getHttpConnectionManager().getParams().setSoTimeout(20000);
	}

	/**
	 * gxp 2013-12-16 下午3:56:17
	 * @param args
	 */
	public static void main(String[] args) {
		//test
		try {
			//base64();
			//getHtml();
			getPageSource();
			//httpclientGet();
			//httpclientPost();
			//testHasAnImageSearchPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void getHtml() throws Exception{
		String url="http://zhili.yofogo.com/";
		url="http://www.mof.gov.cn/zhengwuxinxi/bulinggonggao/tongzhitonggao/";
		// url="http://item.taobao.com/item.htm?spm=a215f.6985601.1995840397.2.sjZu07";
		url="http://js.iwififree.com/wifiPortal/portal.jhtml?alipaytoken=alipaytoken888&test=gggxp你好吗？";
		url="http://goo.hao61.net/index/?sty_from=1&app_from=a1950ea19819647a658049f93d93aea93fbe4ad6&search_from=1&appbrowser_from=1&imei=&idfa=&swi=1&tab=1&tp=&brd=";
		Document doc=null;
		//doc=Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").header("Accept-Encoding", "").get();
		doc=Jsoup.connect(url).header("User-Agent", "Alipay OTP Client").header("Accept-Encoding", "gzip,deflate,sdch").header("Accept", "text/html,application/xhtml+xml,application/json,application/xml;q=0.9,image/webp,*/*;q=0.8").get();
		System.out.println(doc.html());
		/**
		Document doc = Jsoup.connect("http://example.com")
				  .data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();
		 */
	}
	private static void httpclientGet() throws Exception{
		String url="http://js.iwififree.com/wifiPortal/portal.jhtml?alipaytoken=alipaytoken888&test=gggxp你好吗？";
		url="http://60.191.53.35:8750/unionmanager";
		url="http://js.iwififree.com/wifiPortal/portal.jhtml";
		url="http://goo.hao61.net/index/?sty_from=1&app_from=a1950ea19819647a658049f93d93aea93fbe4ad6&search_from=1&appbrowser_from=1&imei=&idfa=&swi=1&tab=1&tp=&brd=";
		GetMethod get=new GetMethod(url);  
		//get.setQueryString("alipaytoken=alipaytoken888&h3cacip=235&apname="+URLEncoder.encode("你好ret8","utf-8"));
		//get.setRequestHeader("User-Agent", "Alipay OTP Client");
		try{
			int statusCode=client.executeMethod(get);
			System.out.println("statusCode:"+statusCode);
			System.out.println("location:"+get.getResponseHeader("Location"));
			if(statusCode==200){
				System.out.println("body:"+get.getResponseBodyAsString());
			}
		}catch(Exception e){
			throw e;
		}finally{
			get.releaseConnection();
			get=null;
		}
	}
	private static void httpclientPost() throws Exception{
		String url="http://js.iwififree.com/wifiPortal/portal.jhtml?alipaytoken=alipaytoken888&test=gggxp你好吗？";
		url="http://60.191.53.35:8750/unionmanager";
		//url="http://js.iwififree.com/wifiPortal/portal.jhtml?alipaytoken=alipaytoken888&test="+URLEncoder.encode("gggxp我","utf-8");
		url="http://goo.hao61.net/index/?sty_from=1&app_from=a1950ea19819647a658049f93d93aea93fbe4ad6&search_from=1&appbrowser_from=1&imei=&idfa=&swi=1&tab=1&tp=&brd=";
		PostMethod post=new PostMethod(url); 
		post.setRequestHeader("User-Agent", "Alipay OTP Client");
		try{
			int statusCode=client.executeMethod(post);
			System.out.println("statusCode:"+statusCode);
			System.out.println("location:"+post.getResponseHeader("Location"));
			System.out.println("Serve:"+post.getResponseHeader("Server"));
			System.out.println("Refresh:"+post.getResponseHeader("Refresh"));
			if(statusCode==200){
				System.out.println("body:"+post.getResponseBodyAsString());
			}
		}catch(Exception e){
			throw e;
		}finally{
			post.releaseConnection();
			post=null;
		}
	}
	private static void getPageSource(){
		String url="http://zhili.yofogo.com/";
		//url="http://www.mof.gov.cn/zhengwuxinxi/bulinggonggao/tongzhitonggao/?jjj=2";
		//url="http://item.taobao.com/item.htm?spm=a215f.6985601.1995840397.2.sjZu07";
		//url="http://www.weatao.com";
		//url="http://www.weather.com.cn/photo/2013/12/gqt/2014066.shtml";
		url="http://js.iwififree.com/wifiPortal/index.jsp";
		url="http://vdisk.weibo.com/";
		url="http://js.iwififree.com/wifiPortal/portal.jhtml?alipaytoken=alipaytoken888&test=gggxp你好吗？";
		url="http://60.191.53.35:8750/unionmanager";
		url="http://goo.hao61.net/index/?sty_from=1&app_from=a1950ea19819647a658049f93d93aea93fbe4ad6&search_from=1&appbrowser_from=1&imei=&idfa=&swi=1&tab=1&tp=&brd=";
		WebDriver driver =new HtmlUnitDriver();
		driver.get(url);
		System.out.println("{"+driver.getTitle()+"}");
		String html = driver.getPageSource();
		System.out.println(html);
	}
	private static void testHasAnImageSearchPage() throws Exception {  
		String url="http://www.baidu.com/";
		WebDriver page = new HtmlUnitDriver(); 
		page.get(url);  
		WebElement searchBox = page.findElement(By.name("wd"));  
		searchBox.sendKeys("JavaEye");  


		WebElement subBtn = page.findElement(By.id("su"));  
		subBtn.submit();  
		//WebElement result = page.findElement(By.linkText("http://www.iteye.com")); 
		WebElement result = page.findElement(By.linkText("javaeye_百度百科"));
		System.out.println("result:"+result.getAttribute("href"));
		//assertNotNull(result);  
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
		String e = Base64.encodeBase64String(b);
		String d = new String(Base64.decodeBase64(e));

		System.out.println("密文e：[" + e+"]"+e.length());
		System.out.println("原文s：[" + s+"]");
		System.out.println("原文d：[" + d+"]");
	}

}
