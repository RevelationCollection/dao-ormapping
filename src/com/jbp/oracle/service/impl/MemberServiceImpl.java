package com.jbp.oracle.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jbp.oracle.dao.IMemberDAO;
import com.jbp.oracle.dao.impl.MemberDAOImpl;
import com.jbp.oracle.factory.DAOFactory;
import com.jbp.oracle.service.IMemberService;
import com.jbp.oracle.vo.Member;

public class MemberServiceImpl implements IMemberService {

	@Override
	public boolean add(Member vo) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		if(dao.findById(vo.getMid())==null){
			if(dao.findByPhone(vo.getPhone())==null){
				if(vo.getAge()<=0&&vo.getAge()<150){
					vo.setAge(-1);
				}
				return dao.doCreate(vo);
			}
		}
		return false;
	}

	@Override
	public boolean edit(Member vo) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		Member temp = dao.findByPhone(vo.getPhone());
		if(temp==null){
			return dao.doUpdate(vo);
		}else{
			if(temp.getMid().equals(vo.getMid())){
				return dao.doUpdate(vo);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Set<String> ids) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		if(ids.size()==0||ids==null){
			return false;
		}
		return dao.doRemove(ids);
	}
	
	@Override
	public Member get(String id) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		return dao.findById(id);
	}

	@Override
	public List<Member> list() throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		return dao.findAll();
	}

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allMembers", dao.findAll(currentPage, lineSize));
		map.put("memberCount", dao.getAllCount());
		return map;
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if(column==null||keyWord==null||"".equals(keyWord)){
			map.put("allMembers", dao.findAll(currentPage, lineSize));
			map.put("memberCount", dao.getAllCount());
		}else{
			map.put("allMembers", dao.findAll(column, keyWord, currentPage, lineSize));
			map.put("memberCount", dao.getAllCount(column, keyWord));
		}
		return map;
	}

}
