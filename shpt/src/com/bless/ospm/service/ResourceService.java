package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.dto.ParentDto;
import com.bless.ospm.dto.TreeJSDto;
import com.bless.ospm.dto.ResourceTreeJSDto;
import com.bless.ospm.model.base.Resource;

public interface ResourceService extends BaseService {
	/**
	 * 根据父结点得到所有的子节点。根节点编号为0
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Resource> findPrivilegeTreeById(long parentId);
	
	public List<Resource> findAllLeaf();
	
	/**
	 * 得到资源树
	 * @return
	 */
	public ResourceTreeJSDto findResourceTree();
	/**
	 * 加载权限树
	 * @return
	 */
	public TreeJSDto resourceTree();
	/**
	 * 获得权限父级树
	 * @return
	 */
	public List<Resource> resouceAll();
	/**
	 * 删除权限,只修改状态
	 * @param resource
	 * @return
	 */
	public String delResource(Resource resource);
	/**
	 * 修改权限信息
	 * @param resource
	 * @return
	 */
	public String updateResouce(Resource resource);
	
}
