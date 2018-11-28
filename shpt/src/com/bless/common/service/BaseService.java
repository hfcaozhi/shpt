package com.bless.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import com.bless.ospm.action.Page;

public interface BaseService {
	//获取所有的内容
	public List<?> findAll(Class clazz);
	public void insert(Object obj);
	public void update(Object obj);
	public void saveOrUpdate(Object obj);
	public void delete(Class<?> objClass,Serializable id);
	public Object get(Class<?> objClass,Serializable id);
	public Object load(Class<?> objClass,Serializable id);
	public void saveOrUpdateAll(Collection<?> collection);
	public void deleteAll(Collection<?> collection);
	
	public int cudByHql(String hql,Map<String,Object> map);
	
	public List<?> findByHql(String hql,Map<String,Object> map,int pageSize,int pageNo);
	public Page findByHqlPage(String hql,Map<String,Object> map,Page page);
	
	
	public int cudBySql(String sql,Map<String,Object> map);
	public List<?> findBySql(String sql,Map<String,Object> map,int pageSize,int pageNo);
	public Object getCountByHql(String hql,Map<String,Object> map,int pageSize,int pageNo);
	public Object getCountBySql(String sql,Map<String,Object> map,int pageSize,int pageNo);
	
	public List<?> findByPropertiesEq(Class<?> objClass,Map<String,Object> proMap,Map<String,String> orderByMap,int pageSize,int pageNo);
	public List<?> findByCriteria(Criteria criteria,Map<String,String> orderByMap,int pageSize,int pageNo);
	public Page findBySqlPage(String sql, Map<String, Object> map, Page page);
	public Page findPageBySpecificClass(String hql, Page page, Class target);
}
