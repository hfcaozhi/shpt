package com.bless.ospm.model.base;

// Generated 2013-5-24 10:46:36 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Organization generated by hbm2java
 */
@Entity
@Table(name = "organization", catalog = "shpt")
public class Organization implements java.io.Serializable {

	private Long id;
	private String name;
	private String description;
	private Long parentId;
	private Set<UserOrgRef> userOrgRefs = new HashSet<UserOrgRef>(0);
	

	public Organization() {
	}

	public Organization(String name, String description, Long parentId,
			Set<UserOrgRef> userOrgRefs) {
		this.name = name;
		this.description = description;
		this.parentId = parentId;
		this.userOrgRefs = userOrgRefs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 40)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<UserOrgRef> getUserOrgRefs() {
		return this.userOrgRefs;
	}

	public void setUserOrgRefs(Set<UserOrgRef> userOrgRefs) {
		this.userOrgRefs = userOrgRefs;
	}

	

}
