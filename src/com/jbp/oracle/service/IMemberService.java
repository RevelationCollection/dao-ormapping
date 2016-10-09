package com.jbp.oracle.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jbp.oracle.vo.Member;

public interface IMemberService {
	/**
	 * 增加Vo数据，考虑的问题有：
	 * 1、需要考虑添加的MID不能和数据库中的重复<br>
	 * 2、电话不能喝数据库中的重复<br>
	 * 3、年龄不允许大于150和小于0,如果超过范围将年龄设为-1，以便于区分<br>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Member vo) throws Exception;
	/**
	 * 更新数据
	 * 1、要更新的数据电话不能和数据库中的电话重复不允许更新
	 * 2、如果电话重复且用户名相同允许更新
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Member vo) throws Exception;
	/**
	 * 批量删除数据库的数据，要包装所要删除的数据不能为空，如果为空直接返回fasle
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Set<String> ids) throws Exception;
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Member get(String id) throws Exception;
	/**
	 * 查询全部信息
	 * @return
	 * @throws Exception
	 */
	public List<Member> list() throws Exception;
	/**
	 * 分页显示
	 * @param currentPage 显示的页数
	 * @param lineSize 每页显示的数量
	 * @return Map集合
	 * 1、key=allMembers  value= 要显示数据集合
	 * 2、key=memberCount value= 查询出的数据量
	 * @throws Exception
	 */
	public Map<String,Object> list(Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 模糊查询+分页显示
	 * @param column 查询的字段
	 * @param keyWord 关键字
	 * @param currentPage 显示的页数
	 * @param lineSize 每页显示的数量
	 * @return Map集合
	 * 1、key=allMembers  value= 要显示数据集合
	 * 2、key=memberCount value= 查询出的数据量
	 * @throws Exception
	 */
	public Map<String,Object> list(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
	
}
