package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

import com.bless.ospm.model.base.RoleResourceRef;
import com.bless.ospm.model.base.UserRoleRef;

public class RoleDto {
	private Long id;
	private String name;
	private String description;
	private Byte status;
	private List<UserRoleRef> userRoleRefs = new ArrayList<UserRoleRef>(0);
	private List<RoleResourceRef> roleResourceRefs = new ArrayList<RoleResourceRef>(
			0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public List<UserRoleRef> getUserRoleRefs() {
		return userRoleRefs;
	}

	public void setUserRoleRefs(List<UserRoleRef> userRoleRefs) {
		this.userRoleRefs = userRoleRefs;
	}

	public List<RoleResourceRef> getRoleResourceRefs() {
		return roleResourceRefs;
	}

	public void setRoleResourceRefs(List<RoleResourceRef> roleResourceRefs) {
		this.roleResourceRefs = roleResourceRefs;
	}

}
