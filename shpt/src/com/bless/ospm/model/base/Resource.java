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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resource generated by hbm2java
 */
@Entity
@Table(name = "resource", catalog = "shpt")
public class Resource implements java.io.Serializable {

	private Long id;
	private String name;
	private String actionUrl;
	private String description;
	private Byte status;
	private Byte isLeaf;
	private Long parentId;
	private Set<RoleResourceRef> roleResourceRefs = new HashSet<RoleResourceRef>(
			0);
	private Set<Menu> menus = new HashSet<Menu>(0);

	public Resource() {
	}

	public Resource(String name, String actionUrl, String description,
			Byte status, Byte isLeaf, Long parentId,
			Set<RoleResourceRef> roleResourceRefs, Set<Menu> menus) {
		this.name = name;
		this.actionUrl = actionUrl;
		this.description = description;
		this.status = status;
		this.isLeaf = isLeaf;
		this.parentId = parentId;
		this.roleResourceRefs = roleResourceRefs;
		this.menus = menus;
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

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "action_url", length = 200)
	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	@Column(name = "description", length = 400)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "is_leaf")
	public Byte getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Byte isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<RoleResourceRef> getRoleResourceRefs() {
		return this.roleResourceRefs;
	}

	public void setRoleResourceRefs(Set<RoleResourceRef> roleResourceRefs) {
		this.roleResourceRefs = roleResourceRefs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
