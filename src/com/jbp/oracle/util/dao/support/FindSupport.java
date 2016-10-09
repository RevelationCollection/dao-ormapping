package com.jbp.oracle.util.dao.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class FindSupport{
	public <V>void setPreparedStatemetn(PreparedStatement pstmt,V id){
		try {
			String type = id.getClass().getSimpleName();
			if("String".equals(type)){
				pstmt.setString(1, id.toString());
			}else if("Integer".equals(type)||"int".equals(type)){
				pstmt.setInt(1,Integer.parseInt(id.toString()));
			} 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setPreparedStatement(PreparedStatement pstmt,Object ... args){
		try {
			for (int i = 0; i < args.length; i++) {
				String type = args[i].getClass().getSimpleName();
				if("String".equals(type)){
					pstmt.setString(i+1, args[i].toString());
				}else if("Integer".equals(type)||"int".equals(type)){
					pstmt.setInt(i+1, Integer.parseInt(args[i].toString()));
				}else if("Double".equalsIgnoreCase(type)){
					pstmt.setDouble(i+1, Double.parseDouble(args[i].toString()));
				}else if("Date".equals(type)){
					Date date = (Date)args[i];
					pstmt.setDate(i+1, new java.sql.Date(date.getTime()));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}