package com.jbp.oracle.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultValueToVoUtils{
	private ResultValueToVoUtils(){}
	public static <T>T getVo(Class<T> cls,PreparedStatement pstmt){
		try {
			ResultSetMetaData rsmd = pstmt.getMetaData();
			ResultSet rs = pstmt.executeQuery();
			T t = null;
			while(rs.next()){
				t = cls.newInstance();
				for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
//					System.out.println(rsmd.getColumnLabel(i).getClass().getSimpleName());
//					System.out.println(cls.getDeclaredField(rsmd.getColumnLabel(i).toLowerCase()).getType().getSimpleName());
//					String type = rsmd.getColumnLabel(i).getClass().getSimpleName();
					String type = cls.getDeclaredField(rsmd.getColumnLabel(i).toLowerCase()).getType().getSimpleName();
					if("String".equals(type)){
						BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(),rs.getString(rsmd.getColumnLabel(i)) );
					}else if("int".equals(type)||"Integer".equals(type)){
						BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getInt(rsmd.getColumnLabel(i)));
					}else if("Double".equalsIgnoreCase(type)){
						BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getDouble(rsmd.getColumnLabel(i)));
					}else if("Date".equals(type)){
						BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(),rs.getDate(rsmd.getColumnLabel(i)));
					}
				}
				return t;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static<T> List<T> getVoAll(Class<T> cls,PreparedStatement pstmt){
		try {
			ResultSetMetaData rsmd = pstmt.getMetaData();
			ResultSet rs = pstmt.executeQuery();
			T t = null;
			List<T> all = new ArrayList<T>();
			while(rs.next()){
				
					t = cls.newInstance();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						try {
						String type = cls.getDeclaredField(rsmd.getColumnLabel(i).toLowerCase()).getType().getSimpleName();
						if("String".equals(type)){
							BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getString(rsmd.getColumnLabel(i)));
						}else if("Integer".equals(type)||"int".equals(type)){
							BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getInt(rsmd.getColumnLabel(i)));
						}else if("Double".equals(type)){
							BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getDouble(rsmd.getColumnLabel(i)));
						}else if("Date".equals(type)){
							BeanValueUtils.setMethod(t, rsmd.getColumnLabel(i).toLowerCase(), rs.getDate(rsmd.getColumnLabel(i)));
						}
						} catch (Exception e) {
						}
					}
					all.add(t);
				
			}
			return all;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static Object converteStat(PreparedStatement pstmt) throws Exception {
		ResultSet rs = pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getString(1) ;
		}
		return "0" ;
	}
}