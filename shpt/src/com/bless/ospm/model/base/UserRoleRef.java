package com.bless.ospm.model.base;

// Generated 2013-5-17 11:14:35 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRoleRef generated by hbm2java
 */
@Entity
@Table(name = "user_role_ref", catalog = "shpt")
public class UserRoleRef implements java.io.Serializable {

	private long id;
	private User user;
	private Role role;

	public UserRoleRef() {
	}

	public UserRoleRef(long id) {
		this.id = id;
	}

	public UserRoleRef(long id, User user, Role role) {
		this.id = id;
		this.user = user;
		this.role = role;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
