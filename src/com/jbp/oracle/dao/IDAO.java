package com.jbp.oracle.dao;

import java.util.List;
import java.util.Set;

public interface IDAO <K,V>{
	public boolean doCreate(V vo) throws Exception;
	public boolean doUpdate(V vo) throws Exception;
	public boolean doRemove(Set<K> ids) throws Exception;
	public V findById(K id) throws Exception;
	public List<V> findAll() throws Exception;
	public List<V> findAll(Integer currentPage,Integer lineSize) throws Exception;
	public List<V> findAll(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
	public Long getAllCount() throws Exception;
	public Long getAllCount(String column,String keyWord) throws Exception;
}
