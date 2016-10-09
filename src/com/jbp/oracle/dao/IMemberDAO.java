package com.jbp.oracle.dao;

import com.jbp.oracle.vo.Member;

public interface IMemberDAO extends IDAO<String, Member> {
	/**
	 * 根据自己的需求扩充自己的特色方法
	 * 根据电话查询Vo
	 * @param Phone 
	 * @return
	 * @throws Exception
	 */
	public Member findByPhone(String Phone) throws Exception;
}
