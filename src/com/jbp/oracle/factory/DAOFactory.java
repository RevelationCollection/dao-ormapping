package com.jbp.oracle.factory;

public class DAOFactory {
	private DAOFactory(){}
	//DAO工厂类，私有化构造方法，减少不必要的实例化对象。
	public static <T>T getInstance(Class<T> cls){
		try {
			return cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
