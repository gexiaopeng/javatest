/*
 * @(#)Data.java 2015-1-31下午11:32:50
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package cn.gxp.udp.h3client;

import java.net.UnknownHostException;



/**
 * AC数据报文
 * 傲天动联、华三
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2015-1-31下午11:32:50 TODO</li>
 * </ul> 
 */

public class AcData{

	//private static Logger log = Logger.getLogger(AcData.class);	// 日志
	private static short serialNum = -32768;

	private byte ver = 0x01;	// 协议版本号（16进制字符）,默认为ipV4报文
	private byte type;	// 报文类型（16进制字符）
	private byte authType = 0x00;	// 认证方式（16进制字符），默认为chap
	private byte rsv = 0;	// 保留字段
	private short serialNo;	// 报文的序列号（我方来生成，同一认证流程中该seriaNo相同）
	private short reqId;	// 请求ID，由AC设备随机生成
	private String userIp;
	private short userPort = 0;	// 保留
	private byte errCode;	// 错误码
	private byte attrNum;	// 属性数量
	private Attr attr;	// 属性

	// extends filds
	private boolean isResponse = false;	// 请求是否有响应

	public class Attr {
		private String userName;	// 用户名（长度<=253）
		private String passWord;	// 密码（长度<=16）
		private String challenge;	// chap方式加密的魔术字（长度16）
		private String chapPassWord;	// chap加密后的密码（长度16）
		private String errId;	// 错误标识（长度5）ACXXX：ac产生的错误；RDXXX：ac透传radius认证错误

		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		public String getChallenge() {
			return challenge;
		}
		public void setChallenge(String challenge) {
			this.challenge = challenge;
		}
		public String getChapPassWord() {
			return chapPassWord;
		}
		public void setChapPassWord(String chapPassWord) {
			this.chapPassWord = chapPassWord;
		}
		public String getErrId() {
			return errId;
		}
		public void setErrId(String errId) {
			this.errId = errId;
		}

	}

	public synchronized short getSerialNo() {
		if (serialNo == 0) {	// 未初始化
			int addNum = 1;	// 每次递增的数字（用于多台服务器生成该serialNo的机制不同）
			try {
				//if (Globals.getLocalIp().equals("192.168.53.201")) {
					//addNum = 2;
				//}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//log.error("获取本地服务器Ip异常！", e);
			}
			serialNum += addNum;
			if (serialNum >= (32768 - addNum)) {	// 32767是short最大数
				serialNum = -32768;
				serialNum += addNum;
			} else if (serialNum == 0){
				serialNum += addNum;
			}
			serialNo = serialNum;
		}
		return serialNo;
	}
	public void setSerialNo(short serialNo) {
		this.serialNo = serialNo;
	}
	public byte getVer() {
		return ver;
	}
	public void setVer(byte ver) {
		this.ver = ver;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getAuthType() {
		return authType;
	}
	public void setAuthType(byte authType) {
		this.authType = authType;
	}
	public byte getRsv() {
		return rsv;
	}
	public void setRsv(byte rsv) {
		this.rsv = rsv;
	}
	public short getReqId() {
		return reqId;
	}
	public void setReqId(short reqId) {
		this.reqId = reqId;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public short getUserPort() {
		return userPort;
	}
	public void setUserPort(short userPort) {
		this.userPort = userPort;
	}
	public byte getErrCode() {
		return errCode;
	}
	public void setErrCode(byte errCode) {
		this.errCode = errCode;
	}
	public byte getAttrNum() {
		return attrNum;
	}
	public void setAttrNum(byte attrNum) {
		this.attrNum = attrNum;
	}
	public Attr getAttr() {
		if (attr == null) {
			attr = new Attr();
		}
		return attr;
	}
	public void setAttr(Attr attr) {
		this.attr = attr;
	}
	public boolean isResponse() {
		return isResponse;
	}
	public void setResponse(boolean isResponse) {
		this.isResponse = isResponse;
	}

}
