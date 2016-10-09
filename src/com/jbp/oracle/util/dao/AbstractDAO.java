package com.jbp.oracle.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.jbp.oracle.dbc.DatabaseConnection;
import com.jbp.oracle.util.PrimaryKey;
import com.jbp.oracle.util.ResultValueToVoUtils;
import com.jbp.oracle.util.dao.support.CreateSupport;
import com.jbp.oracle.util.dao.support.FindSupport;
import com.jbp.oracle.util.dao.support.RemoveSupport;
import com.jbp.oracle.util.dao.support.UpdateSupport;

public abstract class AbstractDAO {
	protected Connection conn;
	protected PreparedStatement pstmt;
	public AbstractDAO(){
		conn = DatabaseConnection.getConnection();
	}
	public boolean createSupport(Object vo){
		try {
			CreateSupport  sup = new CreateSupport(vo);
			String sql = sup.createSQL();
			this.pstmt = this.conn.prepareStatement(sql);
			sup.setPreparedStatement(pstmt);
			return pstmt.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateSupport(Object vo){	
		try {
			UpdateSupport sup = new UpdateSupport(vo);
			String sql = sup.creatSQL();
			this.pstmt = this.conn.prepareStatement(sql);
			sup.setPreparedStatement(this.pstmt);
			return pstmt.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return false;
	}
	public	<T> boolean removeSupport(Class<T> cls,Set<?> ids){
		try {
			RemoveSupport rs = new RemoveSupport();
			String sql  = rs.createSQL(cls, ids);
			this.pstmt = this.conn.prepareStatement(sql);
			rs.setPreparedStatement(pstmt,
					cls.getDeclaredField(PrimaryKey.getPrmaryKey(cls.getName())).getType().getSimpleName());
			int count = this.pstmt.executeUpdate();
			return count==ids.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public <T,V> T findByIdSupport(String sql,V id,Class<T> cls){
		try {
			FindSupport fs = new FindSupport();
			this.pstmt = this.conn.prepareStatement(sql);
			fs.setPreparedStatemetn(pstmt, id);
			return ResultValueToVoUtils.getVo(cls, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public <T> List<T> findAllSupport(String sql,Class<T> cls,Object ... args){
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			if(args.length>0){
				FindSupport fs = new FindSupport();
				fs.setPreparedStatement(pstmt, args);
			}
			return ResultValueToVoUtils.getVoAll(cls, pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public <T> T getSupport(String sql,Class<T> cls,Object ...args) throws Exception {
		this.pstmt = this.conn.prepareStatement(sql) ;
		if (args.length > 0 ) {
			FindSupport support = new FindSupport() ;
			support.setPreparedStatement(this.pstmt, args);
		}
		Object val = ResultValueToVoUtils.converteStat(this.pstmt) ;
		if ("Long".equalsIgnoreCase(cls.getSimpleName())) {
			val = Long.parseLong(val.toString()) ;
		} else if ("int".equals(cls.getSimpleName()) || "Integer".equals(cls.getSimpleName())) {
			val = Integer.parseInt(val.toString()) ;
		}
		return (T) val  ;
	}
}
