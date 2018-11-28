package com.bless.common.service.impl;




import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.BaseService;
import com.bless.ospm.action.Page;

public abstract class BaseServiceImpl implements BaseService{
	

	
	protected  BaseDao dao;
	
	public BaseServiceImpl(){}
	
	public BaseServiceImpl(BaseDao dao){
		this.dao = dao;
	}
	
	public abstract void setBaseDao(BaseDao baseDao);
	@Override
	public List<?> findAll(Class clazz){
		//获取所有的内容
		return dao.findByHql("FROM "+clazz.getName(), null, 0, 0);
	}

	@Override
	public void insert(Object obj) {
		dao.insert(obj);
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		dao.update(obj);
	}

	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(obj);
	}

	@Override
	public void delete(Class<?> objClass, Serializable id) {
		// TODO Auto-generated method stub
		dao.delete(objClass, id);
	}

	@Override
	public Object get(Class<?> objClass, Serializable id) {
		return dao.get(objClass, id);
	}

	@Override
	public Object load(Class<?> objClass, Serializable id) {
		// TODO Auto-generated method stub
		return dao.load(objClass, id);
	}

	@Override
	public void saveOrUpdateAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		dao.saveOrUpdateAll(collection);
	}

	@Override
	public void deleteAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		dao.deleteAll(collection);
	}

	@Override
	public int cudByHql(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.cudByHql(hql, map);
	}

	@Override
	public List<?> findByHql(String hql, Map<String, Object> map, int pageSize,
			int pageNo) {
		// TODO Auto-generated method stub
		return dao.findByHql(hql, map, pageSize, pageNo);
	}

	@Override
	public int cudBySql(String sql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return dao.cudBySql(sql, map);
	}

	@Override
	public List<?> findBySql(String sql, Map<String, Object> map, int pageSize,
			int pageNo) {
		// TODO Auto-generated method stub
		return dao.findBySql(sql, map, pageSize, pageNo);
	}

	@Override
	public Object getCountByHql(String hql, Map<String, Object> map,
			int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		return dao.getCountByHql(hql, map, pageSize, pageNo);
	}

	@Override
	public Object getCountBySql(String sql, Map<String, Object> map,
			int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		return dao.getCountBySql(sql, map, pageSize, pageNo);
	}

	@Override
	public List<?> findByPropertiesEq(Class<?> objClass,
			Map<String, Object> proMap, Map<String, String> orderByMap,
			int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		return dao.findByPropertiesEq(objClass, proMap, orderByMap, pageSize, pageNo);
	}

	@Override
	public List<?> findByCriteria(Criteria criteria,
			Map<String, String> orderByMap, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		return dao.findByCriteria(criteria, orderByMap, pageSize, pageNo);
	}
	
	
	@Override
	public Page findByHqlPage(String hql, Map<String, Object> map,
			Page page) {
		
		page.setTotal(Integer.parseInt(dao.getCountByHql(hql, map, 0, 0).toString()));
		page.setRs(dao.findByHql(hql, map, page.getPageSize(), page.getPageNo()));
		
		return page;
	}
	
	@Override
	public Page findBySqlPage(String sql, Map<String, Object> map, Page page) {
		// TODO Auto-generated method stub
//		return dao.findBySql(sql, map, pageSize, pageNo);
		page.setTotal(Integer.parseInt(dao.getCountBySql(sql, map, 0, 0).toString()));
		page.setRs(dao.findBySql(sql, map, page.getPageSize(), page.getPageNo()));
		
		return page;
	}
	
	@Override
	public Page findPageBySpecificClass(String hql, Page page, Class target) {
		return dao.findPageBySpecificClass(hql, page, target);
	}
	

	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
	
	
}
