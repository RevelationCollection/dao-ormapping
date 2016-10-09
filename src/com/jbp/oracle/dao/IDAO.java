package com.jbp.oracle.dao;

import java.util.List;
import java.util.Set;

public interface IDAO <K,V>{
	/**
	 * 根据指定vo对象生成相应的数据库数据
	 * @param vo vo对象
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean doCreate(V vo) throws Exception;
	/**
	 * 更新指定的vo内容
	 * @param vo  vo对象
	 * @return 成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean doUpdate(V vo) throws Exception;
	/**
	 * 根据主键批量删除vo对象
	 * @param ids 不重复的主键ID
	 * @return 成功返回true，失败返回false
	 * @throws Exception
	 */
	public boolean doRemove(Set<K> ids) throws Exception;
	/**
	 * 根据主键ID查询对应的数据
	 * @param id ID
	 * @return vo对象
	 * @throws Exception
	 */
	public V findById(K id) throws Exception;
	/**
	 * 查询全部
	 * @return 返回List集合
	 * @throws Exception
	 */
	public List<V> findAll() throws Exception;
	/**
	 * 分页显示
	 * @param currentPage 当前页数
	 * @param lineSize 每页显示数量
	 * @return 返回List集合
	 * @throws Exception
	 */
	public List<V> findAll(Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 模糊查询分页显示
	 * @param column 要查询的字段
	 * @param keyWord 查询的关键字
	 * @param currentPage 当前页数
	 * @param lineSize 每页显示数量
	 * @return 返回List集合
	 * @throws Exception
	 */
	public List<V> findAll(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 查询数据表中全部的数据量
	 * @return
	 * @throws Exception
	 */
	public Long getAllCount() throws Exception;
	/**
	 * 模糊查询的结果统计
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Long getAllCount(String column,String keyWord) throws Exception;
}
