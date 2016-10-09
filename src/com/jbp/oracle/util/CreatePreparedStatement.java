package com.jbp.oracle.util;

import java.sql.PreparedStatement;
import java.sql.Types;

public class CreatePreparedStatement {
	private CreatePreparedStatement(){}
	/**
	 * PreparedStatement动态设置工具类
	 * @param seq 要设置的索引位置
	 * @param obj vo类的实例化对象
	 * @param pstmt PreparedStatement实例化对象
	 * @param name vo类的变量名称（反射取得索引设置类型和取得vo对应实际内容）
	 */
	public static void set(int seq,Object obj,PreparedStatement pstmt,String name){
		try {
			String type = obj.getClass().getDeclaredField(name).getType().getSimpleName();
			if("Integer".equals(type)||"int".equals(type)){
				pstmt.setInt(seq, BeanValueUtils.getMethod(obj, name, Integer.class));
			}else if("String".equals(type)){
				pstmt.setString(seq, BeanValueUtils.getMethod(obj, name, String.class));
			}else if("double".equals(type)){
				pstmt.setDouble(seq, BeanValueUtils.getMethod(obj, name, Double.class));
			}else if("Date".equals(type)){
				pstmt.setDate(seq, new java.sql.Date(BeanValueUtils.getMethod(obj, name, java.util.Date.class).getTime()));
			}else {
				pstmt.setNull(seq, Types.NULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
