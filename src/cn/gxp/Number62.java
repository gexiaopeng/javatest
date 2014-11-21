package cn.gxp;

import java.util.Stack;

/**
 * <p>
 * Project Name: 超级促销王
 * <br>
 * Description: 
 * <br>
 * File Name: Number62.java
 * <br>
 * Copyright: Copyright (C) 2009 All Rights Reserved.
 * <br>
 * Company: 杭州迅博达数字传媒技术有限公司
 * <br>
 * @author 葛晓鹏
 * @create time：2014-11-21 下午1:08:32
 * @version: v2.1
 * 
 *       Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2014-11-21 下午1:08:32 |葛晓鹏    　　|v2.1        |Create
 * 
 */
public class Number62 {

private static char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();    
      
    /** 
     * 将10进制转化为62进制  
     * @param number  
     * @param length 转化成的62进制长度，不足length长度的话高位补0，否则不改变什么 
     * @return 
     */  
    public static String _10_to_62(long number, int length){  
         Long rest=number;  
         Stack<Character> stack=new Stack<Character>();  
         StringBuilder result=new StringBuilder(0);  
         while(rest!=0){  
             stack.add(charSet[new Long((rest-(rest/62)*62)).intValue()]);  
             rest=rest/62;  
         }  
         for(;!stack.isEmpty();){  
             result.append(stack.pop());  
         }  
         int result_length = result.length();  
         StringBuilder temp0 = new StringBuilder();  
         for(int i = 0; i < length - result_length; i++){  
             temp0.append('0');  
         }  
           
         return temp0.toString() + result.toString();  
  
    }  
      
      
    /** 
     * 将62进制转换成10进制数 
     *  
     * @param ident62 
     * @return 
     */  
    private static String convertBase62ToDecimal( String ident62 ) {  
        long decimal = 0;  
        int base = 62;  
        long keisu = 0;  
        int cnt = 0;  
  
        byte ident[] = ident62.getBytes();  
        for ( int i = ident.length - 1; i >= 0; i-- ) {  
            int num = 0;  
            if ( ident[i] > 48 && ident[i] <= 57 ) {  
                num = ident[i] - 48;  
            }  
            else if ( ident[i] >= 65 && ident[i] <= 90 ) {  
                num = ident[i] - 65 + 10;  
            }  
            else if ( ident[i] >= 97 && ident[i] <= 122 ) {  
                num = ident[i] - 97 + 10 + 26;  
            }  
            keisu = (long) java.lang.Math.pow( (double) base, (double) cnt );  
            decimal += num * keisu;  
            cnt++;  
        }  
        return String.format( "%08d", decimal );  
    }  
      
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
    	
        // TODO Auto-generated method stub  
        System.out.println(Long.MAX_VALUE+" 62System=" +_10_to_62(Long.parseLong("9223372036854775807"), 5));  
        System.out.println("10System=" +convertBase62ToDecimal("AzL8n0Y58m7"));  
    }  
      

}
