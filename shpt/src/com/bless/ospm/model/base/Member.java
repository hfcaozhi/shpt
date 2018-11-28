package com.bless.ospm.model.base;

// Generated 2013-5-17 11:14:35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Member generated by hbm2java
 */
@Entity
@Table(name = "member", catalog = "shpt")
public class Member implements java.io.Serializable {
	private Long id;
	private String patid;
	private String medicalId;
	private String name;
	private String unit;
	private String mobPhone;
	private Integer age;
	private String sex;
	private String marriage;
	private String nationality;
	private Byte status;
	private Date createTime;
	private Date modifyTime;
	private String idcard;
	private String birth;
	private Byte delFlg;
	private Set<MedicalReport> medicalReports = new HashSet<MedicalReport>(0);
	
	public Member() {
		super();
	}
	public Member(Long id, String patid, String medicalId, String name,
			String unit, String mobPhone, Integer age, String sex,
			String marriage, String nationality, byte status, Date createTime,
			Date modifyTime, String idcard, String birth) {
		super();
		this.id = id;
		this.patid = patid;
		this.medicalId = medicalId;
		this.name = name;
		this.unit = unit;
		this.mobPhone = mobPhone;
		this.age = age;
		this.sex = sex;
		this.marriage = marriage;
		this.nationality = nationality;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.idcard = idcard;
		this.birth = birth;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "patid")
	public String getPatid() {
		return patid;
	}
	public void setPatid(String patid) {
		this.patid = patid;
	}
	@Column(name = "medical_id")
	public String getMedicalId() {
		return medicalId;
	}
	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "nuit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "mob_phone")
	public String getMobPhone() {
		return mobPhone;
	}
	public void setMobPhone(String mobPhone) {
		this.mobPhone = mobPhone;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "marriage")
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	@Column(name = "nationality")
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Column(name = "status")
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
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
	@Column(name = "idcard")
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Column(name = "birth")
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Column(name = "del_flg")
	public Byte getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Byte delFlg) {
		this.delFlg = delFlg;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public Set<MedicalReport> getMedicalReports() {
		return medicalReports;
	}
	public void setMedicalReports(Set<MedicalReport> medicalReports) {
		this.medicalReports = medicalReports;
	}
	
	

	
}