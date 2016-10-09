package com.jbp.oracle.util;

import java.util.ResourceBundle;

public class PrimaryKey {
	private PrimaryKey(){
	}
	/**
	 * 取得配置文件中的主键名称
	 * @param className
	 * @return
	 */
	public static String getPrmaryKey(String className){
		return ResourceBundle.getBundle("Primary").getString(className);
	}
}
