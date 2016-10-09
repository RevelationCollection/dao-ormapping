package com.jbp.oracle.factory;

public class DAOFactory {
	private DAOFactory(){}
	public static <T>T getInstance(Class<T> cls){
		try {
			return cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
