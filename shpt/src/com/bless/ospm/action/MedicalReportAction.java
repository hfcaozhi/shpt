package com.bless.ospm.action;


import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.bless.common.action.BaseAction;
import com.bless.ospm.model.base.MedicalReport;
import com.bless.ospm.service.MedicalReportService;


public class MedicalReportAction extends BaseAction {

	/**
	 * 疾病代码action
	 */
	private static final long serialVersionUID = -8035077973837327699L;
	Logger log = LoggerFactory.getLogger(HealthIndicatorAction.class);
	private MedicalReportService medicalReportService;
	private String medicalId;
	/**
	 * 进入历年体检信息页面
	 * @return
	 */
	public String toMedicalReportJS(){
		request.setAttribute("medicalId", medicalId);
		return SUCCESS;  
	}
	public void medicalReportJS() throws IOException{
		String message = JSONObject.fromObject("ss").toString();
		log.info(message);
		this.writeMsg(message);
		
		
	}

	public MedicalReportService getMedicalReportService() {
		return medicalReportService;
	}

	public void setMedicalReportService(MedicalReportService medicalReportService) {
		this.medicalReportService = medicalReportService;
	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	
}
