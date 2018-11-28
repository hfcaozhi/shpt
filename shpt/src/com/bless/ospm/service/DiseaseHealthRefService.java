package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.DiseaseCode;
import com.bless.ospm.model.base.DiseaseHealthRef;


/**
 * 疾病指标关联业务访问接口
 * @author admin
 *
 */

public interface DiseaseHealthRefService extends BaseService{
	/**
	 * 查询跟疾病管理的指标首字母进行查询
	 * @param id
	 * @param name
	 * @return
	 */
	public List<DiseaseHealthRef>  getbyname_like(String id,String name);
	/**
	 * 根据疾病id,指标id,获取关联对象
	 * @param hisdId
	 * @param DisId
	 * @return
	 */
	public DiseaseHealthRef getByhisIdDiseId(String hisdId,String disId);
	/**
	 * 查询某疾病关联下的指标
	 * @param id
	 * @return
	 */
	public List<DiseaseHealthRef> getlistByDisId(String id);
	
	
}
