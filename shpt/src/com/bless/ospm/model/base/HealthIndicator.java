package com.bless.ospm.model.base;

// Generated 2013-5-17 11:14:35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HealthIndicator generated by hbm2java
 */
@Entity
@Table(name = "health_indicator", catalog = "shpt")
public class HealthIndicator implements java.io.Serializable {

	private String id;
	private String name;
	private String maxvalue;
	private String minvalue;
	private String description;
	private Byte status;
	private Date createTime;
	private Date modifyTime;
	private String unit;
	
	
	
	
	public HealthIndicator() {
	}
	public HealthIndicator(String id) {
		this.id = id;
	}
	public HealthIndicator(String id, String name, String maxvalue,
			String minvalue,  String description, Byte status,
			Date createTime, Date modifyTime, String unit) {
		this.id = id;
		this.name = name;
		this.maxvalue = maxvalue;
		this.minvalue = minvalue;
		this.description = description;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.unit = unit;
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
	
	@Column(name = "description", length = 3999)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "status_h")
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
	@Column(name = "max_value", length = 100)
	public String getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}
	@Column(name = "min_value", length = 100)
	public String getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}
	@Column(name = "unit", length = 30)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}