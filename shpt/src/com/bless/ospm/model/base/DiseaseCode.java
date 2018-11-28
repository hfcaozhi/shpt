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
@Table(name = "disease_code", catalog = "shpt")
public class DiseaseCode implements java.io.Serializable {

	private String id;
	private String name;
	private String type;
	private String description;
	private Byte status;
	private Date createTime;
	private Date modifyTime;
	private Set<DiseaseHealthRef> diseaseHealthRef = new HashSet<DiseaseHealthRef>(0);
	
	
	public DiseaseCode(){
	}
	public DiseaseCode(String id) {
		this.id = id;
	}
	public DiseaseCode(String id, String name, String type, String description,
			Byte status, Date createTime, Date modifyTime) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type", length = 100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "description", length = 3999)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "status_d")
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
	public Set<DiseaseHealthRef> getDiseaseHealthRef() {
		return diseaseHealthRef;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "disease_code")
	public void setDiseaseHealthRef(Set<DiseaseHealthRef> diseaseHealthRef) {
		this.diseaseHealthRef = diseaseHealthRef;
	}
	
	
	
	

	
}