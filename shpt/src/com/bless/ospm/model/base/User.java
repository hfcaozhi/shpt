package com.bless.ospm.model.base;

// Generated 2013-5-24 10:46:36 by Hibernate Tools 3.4.0.CR1

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
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "shpt")
public class User implements java.io.Serializable {

	private Long id;
	private String userName;
	private String password;
	private Byte status;
	private String nickName;
	private Long roleId;
	private String roleName;
	private Set<UserRoleRef> userRoleRefs = new HashSet<UserRoleRef>(0);
	private Set<UserOrgRef> userOrgRefs = new HashSet<UserOrgRef>(0);
	private Set<UserMenu> userMenus = new HashSet<UserMenu>(0);

	public User() {
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	public User(String userName, String password, Byte status, String nickName,
			Set<UserRoleRef> userRoleRefs, Set<UserOrgRef> userOrgRefs,
			Set<UserMenu> userMenus) {
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.nickName = nickName;
		this.userRoleRefs = userRoleRefs;
		this.userOrgRefs = userOrgRefs;
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

	@Column(name = "user_name", length = 40)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "nick_name", length = 40)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRoleRef> getUserRoleRefs() {
		return this.userRoleRefs;
	}

	public void setUserRoleRefs(Set<UserRoleRef> userRoleRefs) {
		this.userRoleRefs = userRoleRefs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserOrgRef> getUserOrgRefs() {
		return this.userOrgRefs;
	}

	public void setUserOrgRefs(Set<UserOrgRef> userOrgRefs) {
		this.userOrgRefs = userOrgRefs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserMenu> getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(Set<UserMenu> userMenus) {
		this.userMenus = userMenus;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", status=" + status + ", nickName=" + nickName
				+ ", roleId=" + roleId + ", userRoleRefs=" + userRoleRefs
				+ ", userOrgRefs=" + userOrgRefs + ", userMenus=" + userMenus
				+ "]";
	}

}