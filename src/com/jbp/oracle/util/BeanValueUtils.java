package com.jbp.oracle.util;

import java.lang.reflect.Method;

public class BeanValueUtils {
	private BeanValueUtils(){}
	@SuppressWarnings("unchecked")
	public static <T>T getMethod(Object obj,String name,Class<T> cls){
		Method getMethod;
		try {
			getMethod = obj.getClass().getMethod("get"+StringUtils.initcap(name));
			return (T) getMethod.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void setMethod(Object obj,String methodName,Object value){
		try {
			Method setMethod = obj.getClass().getMethod("set"+StringUtils.initcap(methodName),
					obj.getClass().getDeclaredField(methodName).getType());
			setMethod.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
