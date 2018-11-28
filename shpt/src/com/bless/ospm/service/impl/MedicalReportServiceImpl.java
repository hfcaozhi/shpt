package com.bless.ospm.service.impl;


import java.util.HashMap;
import java.util.List;
import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.MedicalReportDao;
import com.bless.ospm.model.base.MedicalReport;
import com.bless.ospm.service.MedicalReportService;

/**
 * 诊断报告业务访问实现类
 * @author admin
 *
 */
public class MedicalReportServiceImpl extends BaseServiceImpl implements MedicalReportService{
	private MedicalReportDao medicalReportDao;

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao=baseDao;
		
	}

	
	
	public MedicalReportDao getMedicalReportDao() {
		return medicalReportDao;
	}

	public void setMedicalReportDao(MedicalReportDao medicalReportDao) {
		this.medicalReportDao = medicalReportDao;
		setBaseDao(medicalReportDao);
		
	}


	/**
	 * 根据体检号，查询历年的体检
	 * @param medicalId
	 * @return
	 */
	@Override
	public List<MedicalReport> getListByMedicalId(String medicalId) {
		HashMap map = new HashMap();
		String hql="from MedicalReport where  medicalId=  :medicalId order by medicalTime desc";
 
		map.put("medicalId", medicalId);
		List<MedicalReport> list=(List<MedicalReport>) this.findByHql(hql, map, 0, 0);
		return list;
	}
	
	
	
	
}
