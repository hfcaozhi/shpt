package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.HealthIndicator;

/**
 * 健康指标业务访问接口
 * @author admin
 *
 */

public interface HealthIndicatorService extends BaseService {
	/**
	 * 按照姓名进行模糊查询
	 * @param name
	 * @return
	 */
	public List<HealthIndicator>  getbyname_like(String name);
	/**
	 * 查询该健康指标名称是否已经存在
	 * @param userName
	 * @return
	 */
	public int isRegItemExist(String userName);
	/**
	 * 查询所有的健康指标（去掉删除的）
	 * @return
	 */
	public List<HealthIndicator> findlist();

}
