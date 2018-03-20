package com.jwebidai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidUtil {
	
	public static boolean isChinesName(String name){
		String reg = "^([\u4e00-\u9fa5]|[·]){2,15}$";
		return isCorrect(reg,name); 
	}
	
	/**
	 * 判断手机号
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String reg = "^1(3|4|5|7|8)\\d{9}$";
		return isCorrect(reg,phone);  
	}
	
	/**
	 * 简单判断身份证
	 * @return
	 */
	public static boolean isCard(String card) {
	    String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";  
	    return isCorrect(rgx, card);  
	}
	
	/**
	 * 密码校验 
	 * 规则 6-15位字母和数字
	 * */
	public static boolean checkPassword(String str) {
    	return isCorrect("(?=.*\\d)(?=.*[a-zA-Z])^.{6,15}$", str);
	}
	
	//正则验证  
	public static boolean isCorrect(String rgx, String res)  {  
	    Pattern p = Pattern.compile(rgx);  
	    Matcher m = p.matcher(res);  
	    return m.matches();  
	}  
	
	public static void main(String[] args) {
		System.out.println(isPhone("18588451770"));
	}
	
}