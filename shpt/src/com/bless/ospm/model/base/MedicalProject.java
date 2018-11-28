package com.bless.ospm.model.base;

// Generated 2013-5-17 11:14:35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 体检检测项目
 */
@Entity
@Table(name = "medical_project", catalog = "shpt")
public class MedicalProject implements java.io.Serializable {
	private String id;
	private String medicalReport;
	private Date medicalTime;
	private String value;
	private HealthIndicator healthIndicator;
	private String unit;
	private Date createTime;
	private Date modifyTime;
	private Byte status;
	private Byte delFlg;
	
	
	public MedicalProject() {
		super();
	}
	public MedicalProject(String id, String medicalReport, Date medicalTime,
			String value, HealthIndicator healthIndicator, String unit,
			Date createTime, Date modifyTime, Byte status, Byte delFlg) {
		super();
		this.id = id;
		this.medicalReport = medicalReport;
		this.medicalTime = medicalTime;
		this.value = value;
		this.healthIndicator = healthIndicator;
		this.unit = unit;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.status = status;
		this.delFlg = delFlg;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "medical_report")
	public String getMedicalReport() {
		return medicalReport;
	}
	public void setMedicalReport(String medicalReport) {
		this.medicalReport = medicalReport;
	}
	@Column(name = "medical_time")
	public Date getMedicalTime() {
		return medicalTime;
	}
	public void setMedicalTime(Date medicalTime) {
		this.medicalTime = medicalTime;
	}
	@Column(name = "value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "health_indicator")
	public HealthIndicator getHealthIndicator() {
		return healthIndicator;
	}
	public void setHealthIndicator(HealthIndicator healthIndicator) {
		this.healthIndicator = healthIndicator;
	}
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "status")
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	@Column(name = "del_flg")
	public Byte getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Byte delFlg) {
		this.delFlg = delFlg;
	}
	
	
}
