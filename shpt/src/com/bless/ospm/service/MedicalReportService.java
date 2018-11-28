package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.MedicalReport;


/**
 * 诊断报告业务访问接口
 * @author admin
 *
 */

public interface MedicalReportService extends BaseService{
	/**
	 * 根据体检号，查询历年的体检
	 * @param medicalId
	 * @return
	 */
	
	public  List<MedicalReport> getListByMedicalId(String medicalId);
	

	
}
