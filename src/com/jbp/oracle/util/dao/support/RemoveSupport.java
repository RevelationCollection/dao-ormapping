package com.jbp.oracle.util.dao.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.jbp.oracle.util.PrimaryKey;

public class RemoveSupport{
	private Map<Integer,Object> mapTail = new HashMap<Integer,Object>();
	/**
	 * 动态生成SQL语句
	 * @param cls
	 * @param ids
	 * @return
	 */
	public <T>String createSQL(Class<T> cls,Set<?> ids){
		int foot = 1;
		String key = PrimaryKey.getPrmaryKey(cls.getName());
		StringBuffer buf = new StringBuffer("delete from ");
		buf.append(cls.getSimpleName()).append(" where ").append(key).append(" in (");
		Iterator<?> iter = ids.iterator();
		while(iter.hasNext()){
			buf.append("?").append(",");
			mapTail.put(foot++,iter.next());
		}
		buf.delete(buf.length()-1, buf.length());
		buf.append(")");
		return buf.toString();
	}
	/**
	 * 动态设置PreparedStatement的内容
	 * @param pstmt
	 * @param type
	 */
	public void setPreparedStatement(PreparedStatement pstmt,String type){
		try {
			for (int i = 1; i <= mapTail.size(); i++) {
				if("String".equals(type)){
						pstmt.setString(i, mapTail.get(i).toString());
					} else if("Integer".equals(type)||"int".equals(type)){
						pstmt.setInt(i, Integer.parseInt(mapTail.get(i).toString()));
					}
				}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
