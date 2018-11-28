package com.bless.ospm.service;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.DiseaseCode;


/**
 * 疾病代码业务访问接口
 * @author admin
 *
 */

public interface DiseaseCodeService extends BaseService{
	
	/**
	 * 查询该疾病名称是否已经存在
	 * @param userName
	 * @return
	 */
	public int isRegItemExist(String userName);
	
}
