package com.jbp.oracle.util;

public class StringUtils {
	private StringUtils(){}
	public static String initcap(String str){
		if(str==null)
			return null;
		if(str.length()==1)
			return str.toUpperCase();
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
}
