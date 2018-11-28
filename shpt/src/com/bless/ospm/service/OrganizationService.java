package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.dto.NodeDto;
import com.bless.ospm.dto.OrgTreeDto;
import com.bless.ospm.model.base.Organization;

public interface OrganizationService extends BaseService {
	
	/**
	 * 得到该用户所在组织的所有子组织
	 * @param uid
	 * @return
	 */
	public List<Organization> findChildrenByUserId(Long uid);
	/**
	 * 查询用户组织
	 * @param uid
	 * @return
	 */
	public List<NodeDto> findUserOrg(Long uid);
	/**
	 * 修改用户组织
	 * @param uid
	 * @param orgArray
	 * @return
	 */
	public String updateUserOrg(Long uid,String orgArray);
	/**
	 * 修改组织数据
	 * @param organization
	 * @return
	 */
	public String updateOrg(Organization organization);
	/**
	 * 获取所有的组织结构数据
	 * @return
	 */
	public OrgTreeDto orgAll();
	/**
	 * 删除组织
	 * @param id
	 * @return
	 */
	public String delOrg(Long id);
	
	
}
