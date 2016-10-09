package com.jbp.oracle.util.dao.support;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.jbp.oracle.util.CreatePreparedStatement;
import com.jbp.oracle.util.PrimaryKey;

public class CreateSupport{
	private Object vo;
	public CreateSupport(Object vo){
		this.vo  = vo ;
	}
	Map<Integer,String> mapTail = new HashMap<Integer,String>();
	public String createSQL(){
		int foot = 1;
		StringBuffer buf = new StringBuffer();
		StringBuffer bufHead = new StringBuffer();
		StringBuffer bufTail = new StringBuffer();
		buf.append("insert into ").append(this.vo.getClass().getSimpleName()).append(" (");
		String key = PrimaryKey.getPrmaryKey(this.vo.getClass().getName());
		bufHead.append(key).append(",");
		mapTail.put(foot++, key);
		bufTail.append("?").append(",");
		Field voFields[] = this.vo.getClass().getDeclaredFields();
		for (int i = 0; i < voFields.length; i++) {
			if(!(key.equals(voFields[i].getName()))){
				bufHead.append(voFields[i].getName()).append(",");
				mapTail.put(foot++, voFields[i].getName());
				bufTail.append("?").append(",");
			}
		}
		bufHead.delete(bufHead.length()-1,bufHead.length());
		bufTail.delete(bufTail.length()-1, bufTail.length());
		buf.append(bufHead).append(") values (").append(bufTail).append(")");
		return buf.toString();
	}
	public void setPreparedStatement(PreparedStatement pstmt){
		for (int i = 1; i <= mapTail.size(); i++) {
			CreatePreparedStatement.set(i, vo, pstmt, mapTail.get(i));
		}
	}
}       