package com.jbp.oracle.util;

import java.util.ResourceBundle;

public class PrimaryKey {
	private PrimaryKey(){
	}
	public static String getPrmaryKey(String className){
		return ResourceBundle.getBundle("Primary").getString(className);
	}
}
