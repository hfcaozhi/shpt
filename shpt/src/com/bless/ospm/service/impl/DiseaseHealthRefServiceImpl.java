package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.DiseaseCodeDao;
import com.bless.ospm.dao.DiseaseHealthRefDao;
import com.bless.ospm.model.base.DiseaseCode;
import com.bless.ospm.model.base.DiseaseHealthRef;
import com.bless.ospm.service.DiseaseCodeService;
import com.bless.ospm.service.DiseaseHealthRefService;

/**
 * 疾病代码业务访问实现类
 * @author admin
 *
 */
public class DiseaseHealthRefServiceImpl extends BaseServiceImpl implements DiseaseHealthRefService{
	private DiseaseHealthRefDao diseaseHealthRefDao;

	public DiseaseHealthRefDao getDiseaseHealthRefDao() {
		return diseaseHealthRefDao;
	}


	public void setDiseaseHealthRefDao(DiseaseHealthRefDao diseaseHealthRefDao) {
		this.diseaseHealthRefDao = diseaseHealthRefDao;
		setBaseDao(diseaseHealthRefDao);
	}


	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}

	/**
	 * 查询跟疾病管理的指标首字母进行查询
	 * @param id
	 * @param name
	 * @return
	 */
	@Override
	public List<DiseaseHealthRef> getbyname_like(String id, String name) {
		String hql="from  DiseaseHealthRef dis where dis.deseaseCode.id=:id and pinyin(dis.healthIndicator.name) like :name";
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("name", name+"%");
		List<DiseaseHealthRef> list=(List<DiseaseHealthRef>) this.findByHql(hql, map, 0, 0);
		return list;
	}

	/**
	 * 根据疾病id,指标id,获取关联对象
	 * @param hisdId
	 * @param DisId
	 * @return
	 */
	@Override
	public DiseaseHealthRef getByhisIdDiseId(String hisdId, String disId) {
		String hql="from  DiseaseHealthRef dis where dis.deseaseCode.id=:disId and dis.healthIndicator.id=:hisId";
		HashMap map=new HashMap();
		map.put("hisId", hisdId);
		map.put("disId", disId);
		List<DiseaseHealthRef> list=(List<DiseaseHealthRef>) this.findByHql(hql, map, 0, 0);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询某疾病关联下的指标
	 * @param id
	 * @return
	 */
	@Override
	public List<DiseaseHealthRef> getlistByDisId(String id) {
		String hql ="from DiseaseHealthRef dis where dis.deseaseCode.id=:id and dis.healthIndicator.id !=null";
		HashMap map=new HashMap();
		map.put("id", id);
		
		List<DiseaseHealthRef> list=(List<DiseaseHealthRef>) this.findByHql(hql, map, 0, 0);
		
		return list;
	}
	
	
	
	
	
	
}
