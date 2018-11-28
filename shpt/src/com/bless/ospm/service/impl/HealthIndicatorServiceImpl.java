package com.bless.ospm.service.impl;


import java.util.HashMap;
import java.util.List;
import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.HealthIndicatorDao;
import com.bless.ospm.model.base.HealthIndicator;
import com.bless.ospm.service.HealthIndicatorService;

/**
 * 健康指标业务访问实现类
 * @author admin
 *
 */
public class HealthIndicatorServiceImpl extends BaseServiceImpl implements HealthIndicatorService{
	private HealthIndicatorDao healthIndicatorDao;

	

	public void setHealthIndicatorDao(HealthIndicatorDao healthIndicatorDao) {
		this.healthIndicatorDao = healthIndicatorDao;
		setBaseDao(healthIndicatorDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
		
	}
	/**
	 * 根据首字母进行模糊查询
	 * @param name
	 * @return
	 */
	@Override
	public List<HealthIndicator> getbyname_like(String name) {
		HashMap map = new HashMap();
		String hql="from HealthIndicator where  status=0 and pinyin(name) like :name";
 
		map.put("name", name+"%");
		List<HealthIndicator> list=(List<HealthIndicator>) this.findByHql(hql, map, 0, 0);
		return list;
	}
	/**
	 * 查询该健康指标名称是否已经存在
	 * @param userName
	 * @return
	 */
	@Override
	public int isRegItemExist(String userName) {
		HashMap map = new HashMap();
		map.put("name", userName);
		Object obj = this.getCountByHql("from HealthIndicator where status=0 and name like :name",map , 0, 0);
		return Integer.parseInt(obj.toString());
	}
	/**
	 * 查询所有的健康指标（去掉删除的）
	 * @return
	 */
	@Override
	public List<HealthIndicator> findlist() {
		String hql="from HealthIndicator where status =0";
		List<HealthIndicator> list=(List<HealthIndicator>) this.findByHql(hql, null, 0, 0);
		return list;
	}
	
	
	
	
	
	
	
}
