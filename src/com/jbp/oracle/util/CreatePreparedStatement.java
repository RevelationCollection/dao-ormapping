package com.jbp.oracle.util;

import java.sql.PreparedStatement;
import java.sql.Types;

public class CreatePreparedStatement {
	private CreatePreparedStatement(){}
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
