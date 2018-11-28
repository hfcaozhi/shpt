package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.Role;

public interface RoleService extends BaseService {

	/**
	 * 保存权限树
	 * 
	 * @param roleId 角色编号
	 * @param privilegeTreeJS json格式的权限树
	 */
	public void updatePrivilege(Long roleId,String privilegeTreeJS);
	public List<Role> roleAll();
}
