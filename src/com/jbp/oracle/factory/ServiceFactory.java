package com.jbp.oracle.factory;

import com.jbp.oracle.service.proxy.ServiceProxy;

public class ServiceFactory {
	private ServiceFactory(){}
	@SuppressWarnings("unchecked")
	public static <T>T getInstance(Class<T> cls){
		return (T) new ServiceProxy().bind(cls);
	}
 }
