package cn.gxp.udp.h3client;
/**
 * <p>
 * Project Name: wifi-portal
 * <br>
 * Description: 方法的返回结果
 * <br>
 * File Name: ReturnResult.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-4-9 下午1:22:05
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-4-9 下午1:22:05 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class ReturnResult {
	private boolean success;	// 是否成功
	private String code;//代码
	private  String message;//描述
	
	/**
	 * 创建一个新的实例ReturnResult.
	 *
	 */
	public ReturnResult() {
		super();
	}
	/**
	 * 创建一个新的实例ReturnResult.
	 *
	 * @param success
	 * @param code
	 * @param message
	 */
	public ReturnResult(boolean success, String code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String toString(){
		return "ReturnResult{success:"+success+",code:"+code+",message:"+message+"}";
	}
	
}
