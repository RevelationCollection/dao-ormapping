package com.jbp.oracle.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.jbp.oracle.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler{
	private Object real;
	public <T>Object bind(Class<T> cls){
		try {
			this.real = cls.newInstance();
			return Proxy.newProxyInstance(this.real.getClass().getClassLoader(), 
					this.real.getClass().getInterfaces(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Object ret = null;
			try {
				String methodName = method.getName();
				if(methodName.startsWith("add")||
						methodName.startsWith("edit")||
						methodName.startsWith("remove")){
					DatabaseConnection.getConnection().setAutoCommit(false);
					ret = method.invoke(this.real, args);
					DatabaseConnection.getConnection().commit();
				}else{
					ret = method.invoke(this.real, args);
				}
				return ret;
			} catch (Exception e) {
				DatabaseConnection.getConnection().rollback();
			}			
		} catch (Exception e) {
			throw e;
		}finally{
			DatabaseConnection.close();
		}
		return null;
	}

}
