package com.bless.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bless.common.dao.BaseDao;
import com.bless.common.dao.Constant4Dao;
import com.bless.ospm.action.Page;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{

	@Override
	public int cudByHql(String hql, Map<String, Object> map) {
		return this.getQuery(hql, map).executeUpdate();
	}

	@Override
	public int cudBySql(String sql, Map<String, Object> map) {
		return this.getSQLQuery(sql, map).executeUpdate();
	}

	@Override
	public void delete(Class<?> objClass, Serializable id) {
		getHibernateTemplate().delete(this.get(objClass, id));
	}

	@Override
	public void deleteAll(Collection<?> collection) {
		getHibernateTemplate().deleteAll(collection);
	}

	@Override
	public List<?> findByCriteria(Criteria criteria,
			Map<String, String> orderByMap, int pageSize, int pageNo) {
		return getCriteria(criteria, orderByMap, pageSize, pageNo).list();
	}

	@Override
	public List<?> findByHql(String hql, Map<String, Object> map, int pageSize,
			int pageNo) {
		return this.getQuery(hql, map, pageSize, pageNo).list();
	}

	@Override
	public List<?> findByPropertiesEq(Class<?> objClass,Map<String, Object> proMap,
			Map<String, String> orderByMap, int pageSize, int pageNo) {
		// 创建查询对象
		Criteria criteria = this.createCriteria(objClass);
		//条件查询数据
		criteria = this.createCriteriaExpressionEq(criteria, proMap);
		//查询结果
		return getCriteria(criteria, orderByMap, pageSize, pageNo).list();
	}

	@Override
	public List<?> findBySql(String sql, Map<String, Object> map, int pageSize,
			int pageNo) {
		return this.getSQLQuery(sql, map, pageSize, pageNo).list();
	}

	@Override
	public Object get(Class<?> objClass, Serializable id) {
		return getHibernateTemplate().get(objClass, id);
	}

	@Override
	public Object getCountByHql(String hql, Map<String, Object> map,
			int pageSize, int pageNo) {
		String totalHql = hql.substring( hql.toLowerCase().indexOf("from"), hql.length()); 
		totalHql = "SELECT COUNT(*) "+totalHql;
		return this.findByHql(totalHql, map, pageSize, pageNo).get(0);
	}

	@Override
	public Object getCountBySql(String sql, Map<String, Object> map, int pageSize,
			int pageNo) {
		String totalHql = sql.substring( sql.toLowerCase().indexOf("from"), sql.length()); 
		totalHql = "SELECT COUNT(*) "+totalHql;
		return this.findBySql(sql, map, pageSize, pageNo).get(0);
	}

	@Override
	public void insert(Object obj) {
		super.getHibernateTemplate().save(obj);
	}

	@Override
	public Object load(Class<?> objClass, Serializable id) {
		return super.getHibernateTemplate().load(objClass, id);
	}

	@Override
	public void saveOrUpdate(Object obj) {
		super.getHibernateTemplate().saveOrUpdate(obj);
	}

	@Override
	public void saveOrUpdateAll(Collection<?> collection) {
		super.getHibernateTemplate().saveOrUpdateAll(collection);
	}

	@Override
	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Page findPageBySpecificClass(final String hql, final Page page,
			final Class target) {
		
		page.setTotal(getHibernateTemplate().find(hql).size());
		List list = getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						
						query.setFirstResult((page.getPageNo() - 1)
								* page.getPageSize());
						query.setMaxResults(page.getPageSize());
						List list = query.setResultTransformer(new AliasToBeanResultTransformer(target)).list();
						return list;
					}
				});
		page.setRs(list);
		return page;
	}
	
	
	
	
	/*
	 * ----------------------private method---------------------
	 */
	/**
	 * HQL
	 */
	private Query getQuery(String hql, Map<String, Object> map) {
		Query query = this.createQuery(hql);
		query = this.setParameter(query, map);
		return query;
	}
	private Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}
	private Query setParameter(Query query, Map<String, Object> map) {
		if (map != null) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				Object obj = map.get(string);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					query.setParameterList(string, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					query.setParameterList(string, (Object[])obj);
				}else{
					query.setParameter(string, obj);
				}
			}
		}
		return query;
	}
	private Query getQuery(String hql, Map<String, Object> map, int pageSize,
			int pageNo) {
		Query query = this.createQuery(hql);
		query = this.setParameter(query, map);
		query = this.setPageProperty(query, pageSize, pageNo);
		return query;
	}
	/**
	 * 往Query对象中设置分页参数
	 */
	private Query setPageProperty(Query query, int pageSize, int pageNo) {
		if (pageNo != 0 && pageSize != 0) {
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		return query;
	}
	
	/**
	 * SQL
	 */
	private SQLQuery getSQLQuery(String sql, Map<String, Object> map) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setParameter(query, map);
		return query;
	}
	private SQLQuery createSQLQuery(String sql) {
		return getSession().createSQLQuery(sql);
	}
	private SQLQuery setParameter(SQLQuery query, Map<String, Object> map) {
		if (map != null) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				Object obj = map.get(string);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					query.setParameterList(string, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					query.setParameterList(string, (Object[])obj);
				}else{
					query.setParameter(string, obj);
				}
			}
		}
		return query;
	}
	private SQLQuery getSQLQuery(String sql, Map<String, Object> map,
			int pageSize, int pageNo) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setParameter(query, map);
		query = this.setPageProperty(query, pageSize, pageNo);
		return query;
	}
	private SQLQuery setPageProperty(SQLQuery query, int pageSize, int pageNo) {
		if (pageNo != 0 && pageSize != 0) {
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		return query;
	}
	
	
	/**
	 *Criteria
	 */
	/**
	 * 条件查询-多条件排序
	 */
	private Criteria createOrderBy(Criteria criteria,
			Map<String, String> orderByMap) {
		if (orderByMap != null) {
			Set<String> keySet = orderByMap.keySet();
			for (String string : keySet) {
				this.createOrderBy(criteria, string, orderByMap
						.get(string));
			}
		}
		return criteria;
	}
	/**
	 * 条件查询-排序
	 */
	private Criteria createOrderBy(Criteria criteria, String orderByKey,
			String orderByType) {
		if (Constant4Dao.DESC.equalsIgnoreCase(orderByType.trim())) {
			criteria.addOrder(Order.desc(orderByKey));
		} else {
			criteria.addOrder(Order.asc(orderByKey));
		}
		return criteria;
	}
	/**
	 * 条件查询-分页
	 */
	private Criteria createPaging(Criteria criteria, int pageSize, int pageNo) {
		if (pageSize != 0 && pageNo != 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		return criteria;
	}
	private Criteria getCriteria(Criteria criteria,
			Map<String, String> orderByMap, int pageSize, int pageNo) {
		//排序
		criteria = this.createOrderBy(criteria, orderByMap);
		//分页
		criteria = this.createPaging(criteria, pageSize, pageNo);
		return criteria;
	}
	/**
	 * 创建条件查询对象
	 */
	private Criteria createCriteria(Class<?> objClass) {
		return getSession().createCriteria(objClass);
	}
	private Criteria createCriteriaExpressionEq(Criteria criteria,Map<String, Object> proMap){
		if(proMap!=null){
			Set<String> keySet = proMap.keySet();
			for (String string : keySet) {
				criteria.add(Restrictions.eq(string, proMap.get(string)));
			}
		}		
		return criteria;
	}
}
