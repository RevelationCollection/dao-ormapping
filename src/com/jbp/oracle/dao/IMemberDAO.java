package com.jbp.oracle.dao;

import com.jbp.oracle.vo.Member;

public interface IMemberDAO extends IDAO<String, Member> {
	public Member findByPhone(String Phone) throws Exception;
}
