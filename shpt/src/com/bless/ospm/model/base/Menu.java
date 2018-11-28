package com.bless.ospm.model.base;

// Generated 2013-6-3 11:33:12 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu", catalog = "shpt")
public class Menu implements java.io.Serializable {

	private Long id;
	private Resource resource;
	private String name;
	private String description;
	private Long parentId;
	private Long codeNo;
	private Long porder;
	private Byte status;
	private Set<UserMenu> userMenus = new HashSet<UserMenu>(0);

	public Menu() {
	}

	public Menu(Resource resource, String name, String description,
			Long parentId, Long codeNo, Long porder, Byte status,
			Set<UserMenu> userMenus) {
		this.resource = resource;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
		this.codeNo = codeNo;
		this.porder = porder;
		this.status = status;
		this.userMenus = userMenus;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 200)
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

	@Column(name = "code_no")
	public Long getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(Long codeNo) {
		this.codeNo = codeNo;
	}

	@Column(name = "porder")
	public Long getPorder() {
		return this.porder;
	}

	public void setPorder(Long porder) {
		this.porder = porder;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<UserMenu> getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(Set<UserMenu> userMenus) {
		this.userMenus = userMenus;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", resource=" + resource + ", name=" + name
				+ ", description=" + description + ", parentId=" + parentId
				+ ", codeNo=" + codeNo + ", porder=" + porder + ", status="
				+ status + ", userMenus=" + userMenus + "]";
	}
}
