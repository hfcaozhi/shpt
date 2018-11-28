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
 * DeseaseCode generated by hbm2java
 */
@Entity
@Table(name = "medical_report", catalog = "shpt")
public class MedicalReport implements java.io.Serializable {
	private String id;
	private String medicalReport;
	private String medicalId;
	private String patid;
	private Date medicalTime;
	private String medicalResult;
	private String medicalProject;
	private Integer assessStatus;
	private Date assessTime;
	private String assessSuggestion;
	private Integer auditingStatus;
	private Date auditingTime;
	private String auditingSuggestion;
	private String prescribeSuggestion;
	private Integer prescribeStatus;
	private Date createTime;
	private Integer status;
	private Date stampTime;
	private Integer stampStatus;
	private String stampUser;
	private Integer stampCount;
	private Date modifyTime;
	private Byte delFlg;
	private String auditingUser;
	private String assessUser;
	private Set<MedicalProject> medicalProjects = new HashSet<MedicalProject>(0);

	

	public MedicalReport() {
		super();
	}

	public MedicalReport(String id, String medicalReport, String medicalId,
			String patid, Date medicalTime, String medicalResult,
			String medicalProject, Integer assessStatus, Date assessTime,
			String assessSuggestion, Integer auditingStatus, Date auditingTime,
			String auditingSuggestion, String prescribeSuggestion,
			Integer prescribeStatus, Date createTime, Integer status,
			Date stampTime, Integer stampStatus, String stampUser,
			Integer stampCount, Date modifyTime, byte delFlg,
			String auditingUser, String assessUser) {
		super();
		this.id = id;
		this.medicalReport = medicalReport;
		this.medicalId = medicalId;
		this.patid = patid;
		this.medicalTime = medicalTime;
		this.medicalResult = medicalResult;
		this.medicalProject = medicalProject;
		this.assessStatus = assessStatus;
		this.assessTime = assessTime;
		this.assessSuggestion = assessSuggestion;
		this.auditingStatus = auditingStatus;
		this.auditingTime = auditingTime;
		this.auditingSuggestion = auditingSuggestion;
		this.prescribeSuggestion = prescribeSuggestion;
		this.prescribeStatus = prescribeStatus;
		this.createTime = createTime;
		this.status = status;
		this.stampTime = stampTime;
		this.stampStatus = stampStatus;
		this.stampUser = stampUser;
		this.stampCount = stampCount;
		this.modifyTime = modifyTime;
		this.delFlg = delFlg;
		this.auditingUser = auditingUser;
		this.assessUser = assessUser;
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
	@Column(name = "medical_id")
	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	@Column(name = "patid")
	public String getPatid() {
		return patid;
	}

	public void setPatid(String patid) {
		this.patid = patid;
	}
	@Column(name = "medical_time")
	public Date getMedicalTime() {
		return medicalTime;
	}

	public void setMedicalTime(Date medicalTime) {
		this.medicalTime = medicalTime;
	}
	@Column(name = "medical_result")
	public String getMedicalResult() {
		return medicalResult;
	}

	public void setMedicalResult(String medicalResult) {
		this.medicalResult = medicalResult;
	}
	@Column(name = "medical_project")
	public String getMedicalProject() {
		return medicalProject;
	}

	public void setMedicalProject(String medicalProject) {
		this.medicalProject = medicalProject;
	}
	@Column(name = "assess_status")
	public Integer getAssessStatus() {
		return assessStatus;
	}

	public void setAssessStatus(Integer assessStatus) {
		this.assessStatus = assessStatus;
	}
	@Column(name = "assess_time")
	public Date getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(Date assessTime) {
		this.assessTime = assessTime;
	}
	@Column(name = "assess_suggestion")
	public String getAssessSuggestion() {
		return assessSuggestion;
	}

	public void setAssessSuggestion(String assessSuggestion) {
		this.assessSuggestion = assessSuggestion;
	}
	@Column(name = "auditing_status")
	public Integer getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(Integer auditingStatus) {
		this.auditingStatus = auditingStatus;
	}
	@Column(name = "auditing_time")
	public Date getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(Date auditingTime) {
		this.auditingTime = auditingTime;
	}
	@Column(name = "auditing_suggestion")
	public String getAuditingSuggestion() {
		return auditingSuggestion;
	}

	public void setAuditingSuggestion(String auditingSuggestion) {
		this.auditingSuggestion = auditingSuggestion;
	}
	@Column(name = "prescribe_suggestion")
	public String getPrescribeSuggestion() {
		return prescribeSuggestion;
	}

	public void setPrescribeSuggestion(String prescribeSuggestion) {
		this.prescribeSuggestion = prescribeSuggestion;
	}
	@Column(name = "prescbibe_status")
	public Integer getPrescribeStatus() {
		return prescribeStatus;
	}

	public void setPrescribeStatus(Integer prescribeStatus) {
		this.prescribeStatus = prescribeStatus;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "stamp_time")
	public Date getStampTime() {
		return stampTime;
	}

	public void setStampTime(Date stampTime) {
		this.stampTime = stampTime;
	}
	@Column(name = "stamp_status")
	public Integer getStampStatus() {
		return stampStatus;
	}

	public void setStampStatus(Integer stampStatus) {
		this.stampStatus = stampStatus;
	}
	@Column(name = "stamp_user")
	public String getStampUser() {
		return stampUser;
	}

	public void setStampUser(String stampUser) {
		this.stampUser = stampUser;
	}
	@Column(name = "stamp_count")
	public Integer getStampCount() {
		return stampCount;
	}

	public void setStampCount(Integer stampCount) {
		this.stampCount = stampCount;
	}
	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "del_flg")
	public Byte getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Byte delFlg) {
		this.delFlg = delFlg;
	}
	@Column(name = "auditing_user")
	public String getAuditingUser() {
		return auditingUser;
	}

	public void setAuditingUser(String auditingUser) {
		this.auditingUser = auditingUser;
	}
	@Column(name = "assess_user")
	public String getAssessUser() {
		return assessUser;
	}

	public void setAssessUser(String assessUser) {
		this.assessUser = assessUser;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medical_report")
	public Set<MedicalProject> getMedicalProjects() {
		return medicalProjects;
	}

	public void setMedicalProjects(Set<MedicalProject> medicalProjects) {
		this.medicalProjects = medicalProjects;
	}
	

	
}
