package com.jbp.oracle.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultValueToVoUtils{
	private ResultValueToVoUtils(){}
	/**
	 * 反射取得Vo类对象并未Vo类对象赋值
	 * @param cls 对应vo类的类型
	 * @param pstmt PreparedStatement对象
	 * @return
	 */
	public static <T>T getVo(Class<T> cls,PreparedStatement pstmt){
		try {
			//取得用户设置的sql语句中的元数据（数据库中的字段）
			ResultSetMetaData rsmd = pstmt.getMetaData();
			ResultSet rs = pstmt.executeQuery(); //执行数据库查询命令
			T t = null;
			while(rs.next()){
				t = cls.newInstance(); //为vo类实例化
				//循环为Vo类设置内容，索引从1开始
				for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
					//取得本次操作的类型
					String type = cls.getDeclaredField(rsmd.getColumnLabel(i).toLowerCase()).getType().getSimpleName();
					if("String".equals(type)){
						//d调用vo类的set方法，传vo类实例化对象，要设置的方法名称，要保存的内容
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
	/**
	 * 反射取得Vo类对象的集合并未Vo类对象赋值
	 * @param cls 对应vo类的类型
	 * @param pstmt PreparedStatement对象
	 * @return
	 */
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
	/**
	 * 反射取得查询的结果数量（返回int或long）
	 * @param pstmt
	 * @return
	 * @throws Exception
	 */
	public static Object converteStat(PreparedStatement pstmt) throws Exception {
		ResultSet rs = pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getString(1) ;
		}
		return "0" ;
	}
}