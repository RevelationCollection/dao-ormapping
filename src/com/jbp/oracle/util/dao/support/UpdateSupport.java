package com.jbp.oracle.util.dao.support;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.jbp.oracle.util.CreatePreparedStatement;
import com.jbp.oracle.util.PrimaryKey;

public class UpdateSupport {
	private Object vo;
	public UpdateSupport(Object vo){
		this.vo  = vo;
	}
	Map<Integer,String> mapTail = new HashMap<Integer,String>();
	public String creatSQL(){
		try{
			int foot = 1;
			StringBuffer buf = new StringBuffer();
			StringBuffer bufHead = new StringBuffer();
			String key = PrimaryKey.getPrmaryKey(this.vo.getClass().getName());
			Field voFields[] = this.vo.getClass().getDeclaredFields();
			for (int i = 0; i < voFields.length; i++) {
				if(!(key.equals(voFields[i].getName()))){
					bufHead.append(voFields[i].getName()).append("= ?,");
					mapTail.put(foot++, voFields[i].getName());
				}
			}
			bufHead.delete(bufHead.length()-1, bufHead.length());
			mapTail.put(foot++, key);
			buf.append("update ").append(this.vo.getClass().getSimpleName()).append(" set ").append(bufHead).append(" where ").
				append(key).append(" = ?");
			return buf.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void setPreparedStatement(PreparedStatement pstmt){
		for (int i = 1; i <=mapTail.size(); i++) {
			CreatePreparedStatement.set(i, vo, pstmt, mapTail.get(i));
		}
	}
}
