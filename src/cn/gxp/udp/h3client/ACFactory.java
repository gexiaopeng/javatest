/*
 * @(#)ACFactory.java 2014-3-11下午3:06:32
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package cn.gxp.udp.h3client;

import java.io.Serializable;

/**
 * 不同的ac厂家的提交参数
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-3-11下午3:06:32 TODO</li>
 * </ul> 
 */

public class ACFactory implements Serializable{
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int type;	// AC厂家 1-Aruba, 2-Moto, 3-H3c, 4-傲天动联,5- e8-C
	private String acIp;	// ACip
	private String loginUrl;	// 登陆提交URL
	private String loginAppend;	// 登陆提交附加参数
	private String logoutUrl;	// 下线提交URL
	private String logoutAppend;	// 下线提交附加参数
	private String apName;	// 设备名称
	private String clientIp;	// 客户端ip（外网IP，根据request获得）
	private String userMac;	// 用户mac
	
	private String wlanUserIp;	// 客户端私网IP（e8-c必须参数）
	private String userDomain;	// 用户域，仅适用于e8-c
	
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAcIp() {
		return acIp;
	}
	public void setAcIp(String acIp) {
		this.acIp = acIp;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getLoginAppend() {
		return loginAppend;
	}
	public void setLoginAppend(String loginAppend) {
		this.loginAppend = loginAppend;
	}
	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	public String getLogoutAppend() {
		return logoutAppend;
	}
	public void setLogoutAppend(String logoutAppend) {
		this.logoutAppend = logoutAppend;
	}
	public String getApName() {
		return apName;
	}
	public void setApName(String apName) {
		this.apName = apName;
	}
	public String getWlanUserIp() {
		return wlanUserIp;
	}
	public void setWlanUserIp(String wlanUserIp) {
		this.wlanUserIp = wlanUserIp;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public String getUserMac() {
		return userMac;
	}
	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

}
