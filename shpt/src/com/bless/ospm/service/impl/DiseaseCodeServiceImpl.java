package com.bless.ospm.service.impl;

import java.util.HashMap;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.DiseaseCodeDao;
import com.bless.ospm.model.base.DiseaseCode;
import com.bless.ospm.service.DiseaseCodeService;

/**
 * 疾病代码业务访问实现类
 * @author admin
 *
 */
public class DiseaseCodeServiceImpl extends BaseServiceImpl implements DiseaseCodeService{
	private DiseaseCodeDao diseaseCodeDao;



	public void setDiseaseCodeDao(DiseaseCodeDao diseaseCodeDao) {
		this.diseaseCodeDao = diseaseCodeDao;
		setBaseDao(diseaseCodeDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}
	/**
	 * 查询该疾病名称是否已经存在
	 * @param userName
	 * @return
	 */
	@Override
	public int isRegItemExist(String userName) {
		HashMap map = new HashMap();
		map.put("name", userName);
		Object obj = this.getCountByHql("from DiseaseCode where status=0 and name like :name",map , 0, 0);
		return Integer.parseInt(obj.toString());
	}
	
		
		
	

	
	
	
	
	
	
}
