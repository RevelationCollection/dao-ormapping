package com.jbp.oracle.dao.impl;

import java.util.List;
import java.util.Set;

import com.jbp.oracle.dao.IMemberDAO;
import com.jbp.oracle.util.dao.AbstractDAO;
import com.jbp.oracle.vo.Member;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
	//注释内的是原DAO实现方法，添加与区别显示OR-Mapping的作用
	@Override
	public boolean doCreate(Member vo) throws Exception {
//		String sql = "insert into member (mid,name,age,phone,birthday,note) values (?,?,?,?,?,?)" ;
//		this.pstmt = this.conn.prepareStatement(sql);
//		this.pstmt.setString(1, vo.getMid());
//		this.pstmt.setString(2,vo.getName());
//		this.pstmt.setInt(3, vo.getAge());
//		this.pstmt.setString(4, vo.getPhone());
//		this.pstmt.setDate(5, new java.sql.Date(vo.getBirthday().getTime()));
//		this.pstmt.setString(6, vo.getNote());
		return super.createSupport(vo);
	}

	@Override
	public boolean doUpdate(Member vo) throws Exception {
//		String sql = "update member set  name=?,age=?,phone=?,birthday=?,note=?  where mid = ?" ;
//		this.pstmt = this.conn.prepareStatement(sql);		
//		this.pstmt.setString(1,vo.getName());
//		this.pstmt.setInt(2, vo.getAge());
//		this.pstmt.setString(3, vo.getPhone());
//		this.pstmt.setDate(4, new java.sql.Date(vo.getBirthday().getTime()));
//		this.pstmt.setString(5, vo.getNote());
//		this.pstmt.setString(6, vo.getMid());
		return super.updateSupport(vo);
	}

	@Override
	public boolean doRemove(Set<String> ids) throws Exception {
//		StringBuffer sql = new StringBuffer("delete from member where mid in(");
//		Iterator<String> iter = ids.iterator();
//		while(iter.hasNext()){
//			sql.append("'").append(iter.next()).append("'").append(",");
//		}
//		sql.delete(sql.length()-1, sql.length()).append(")");
//		this.pstmt = this.conn.prepareStatement(sql.toString());	
//		return this.pstmt.executeUpdate()>0;
		return super.removeSupport(Member.class,ids);
	}

	@Override
	public Member findById(String id) throws Exception {
//		String sql = "select mid,name,age,phone,birthday,note from member where mid = ?";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		this.pstmt.setString(1, id);
//		Member vo = null;
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			vo = new Member();
//			vo.setMid(rs.getString(1));
//			vo.setName(rs.getString(2));
//			vo.setAge(rs.getInt(3));
//			vo.setPhone(rs.getString(4));
//			vo.setBirthday(rs.getDate(5));
//			vo.setNote(rs.getString(6));
//		}
//		return vo;
		String sql = "select mid,name,age,phone,birthday,note from member where mid = ?";
		return super.findByIdSupport(sql, id, Member.class);
	}

	@Override
	public List<Member> findAll() throws Exception {
//		String sql = "select mid,name,age,phone,birthday,note from member ";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		List<Member> all = new ArrayList<Member>();
//		Member vo = null;
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			vo = new Member();
//			vo.setMid(rs.getString(1));
//			vo.setName(rs.getString(2));
//			vo.setAge(rs.getInt(3));
//			vo.setPhone(rs.getString(4));
//			vo.setBirthday(rs.getDate(5));
//			vo.setNote(rs.getString(6));
//			all.add(vo);
//		}
//		return all;
		String sql = "select mid,name,age,phone,birthday,note from member ";
		return super.findAllSupport(sql, Member.class);
	}

	@Override
	public List<Member> findAll(Integer currentPage, Integer lineSize) throws Exception {
		String sql = "select * from (select mid,name,age,phone,birthday,note,rownum rn from member "
				+ " where rownum <= ?) temp "
				+ " where temp.rn > ?";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		this.pstmt.setInt(1, currentPage*lineSize);
//		this.pstmt.setInt(2, (currentPage-1)*lineSize);
//		List<Member> all = new ArrayList<Member>();
//		Member vo = null;
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			vo = new Member();
//			vo.setMid(rs.getString(1));
//			vo.setName(rs.getString(2));
//			vo.setAge(rs.getInt(3));
//			vo.setPhone(rs.getString(4));
//			vo.setBirthday(rs.getDate(5));
//			vo.setNote(rs.getString(6));
//			all.add(vo);
//		}
		return super.findAllSupport(sql, Member.class, currentPage*lineSize,(currentPage-1)*lineSize);
	}

	@Override
	public List<Member> findAll(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
		String sql = "select * from (select mid,name,age,phone,birthday,note,rownum rn from member "
				+ " where rownum <= ? and "+column+" like ?) temp "
				+ " where temp.rn > ?";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		this.pstmt.setInt(1, currentPage*lineSize);
//		this.pstmt.setString(2, "%"+keyWord+"%");
//		this.pstmt.setInt(3, (currentPage-1)*lineSize);
//		List<Member> all = new ArrayList<Member>();
//		Member vo = null;
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			vo = new Member();
//			vo.setMid(rs.getString(1));
//			vo.setName(rs.getString(2));
//			vo.setAge(rs.getInt(3));
//			vo.setPhone(rs.getString(4));
//			vo.setBirthday(rs.getDate(5));
//			vo.setNote(rs.getString(6));
//			all.add(vo);
//		}
		return super.findAllSupport(sql, Member.class,currentPage*lineSize,"%"+keyWord+"%",(currentPage-1)*lineSize);
	}

	@Override
	public Long getAllCount() throws Exception {
		String sql = "select count(*) from member ";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			return rs.getLong(1);
//		}
		return super.getSupport(sql, Long.class);
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws Exception {
		String sql = "select count(*) from member where "+column+" like ?";
//		this.pstmt = this.conn.prepareStatement(sql);
//		this.pstmt.setString(1, "%"+keyWord+"%");
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			return rs.getLong(1);
//		}
		return super.getSupport(sql, Long.class,"%"+keyWord+"%");
	}

	@Override
	public Member findByPhone(String Phone) throws Exception {
		String sql = "select mid,name,age,phone,birthday,note from member where Phone = ?";
//		this.pstmt = this.conn.prepareStatement(sql);	
//		this.pstmt.setString(1, Phone);
//		Member vo = null;
//		ResultSet rs = this.pstmt.executeQuery();
//		while(rs.next()){
//			vo = new Member();
//			vo.setMid(rs.getString(1));
//			vo.setName(rs.getString(2));
//			vo.setAge(rs.getInt(3));
//			vo.setPhone(rs.getString(4));
//			vo.setBirthday(rs.getDate(5));
//			vo.setNote(rs.getString(6));
//		}
		return super.findByIdSupport(sql, Phone,  Member.class);
	}

}
