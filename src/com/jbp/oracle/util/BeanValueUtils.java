package com.jbp.oracle.util;

import java.lang.reflect.Method;

public class BeanValueUtils {
	private BeanValueUtils(){}
	@SuppressWarnings("unchecked")
	/**
	 * 反射取得并调用get方法（一层）
	 * @param obj 实例化对象
	 * @param name 方法名称
	 * @param cls 返回的类型
	 * @return
	 */
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
	/**
	 * 反射调用set方法（一层）
	 * @param obj
	 * @param methodName
	 * @param value
	 */
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
