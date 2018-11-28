package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.Constant4Ospm;
import com.bless.ospm.dao.ResourceDao;
import com.bless.ospm.dao.RoleResourceRefDao;
import com.bless.ospm.dto.Operate;
import com.bless.ospm.dto.ResourceTreeDto;
import com.bless.ospm.dto.ResourceTreeItem;
import com.bless.ospm.dto.ResourceTreeJSDto;
import com.bless.ospm.dto.TreeItemDto;
import com.bless.ospm.dto.TreeJSDto;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.service.ResourceService;

public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService{
	
	private ResourceDao resourceDao;
	private RoleResourceRefDao roleResourceRefDao;
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
		setBaseDao(resourceDao);
	}

	public void setRoleResourceRefDao(RoleResourceRefDao roleResourceRefDao) {
		this.roleResourceRefDao = roleResourceRefDao;
	}

	@Override
	public List<Resource> findPrivilegeTreeById(long parentId) {
		HashMap map = new HashMap();
		map.put("status", new Byte("0"));
		map.put("parentId", parentId);
		map.put("isLeaf", new Byte("1"));
		return (List<Resource>) resourceDao.findByHql("FROM Resource where status=:status and parentId=:parentId and isLeaf=:isLeaf ", map, 99999, 1);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}

	@Override
	public List<Resource> findAllLeaf() {
		HashMap map = new HashMap();
		map.put("status", Constant4Ospm.STATUS_CODE_NORMAL);
		map.put("isLeaf", Constant4Ospm.STATUS_CODE_UNUSE);
		return (List<Resource>) resourceDao.findByHql("FROM Resource where status=:status and isLeaf=:isLeaf ", map, 0, 0);
	}

	@Override
	public ResourceTreeJSDto findResourceTree() {
		ResourceTreeJSDto btj = new ResourceTreeJSDto();
		HashMap map = new HashMap();
		map.put("status", new Byte("0"));
		List<Resource> rs = (List<Resource>) resourceDao.findByHql("FROM Resource where status=:status ", map, 0, 0);
		btj.setRoot("div.zm_body");
		
		btj.setOps(getOPS(rs));
		
		return btj;
//		return convertToTree(null,rs);
	}

	private List<ResourceTreeItem> getOPS(List<Resource> rs) {
		
		return convertFromTree(convertToTree(null,rs),null).getChildren();
	}
	
	
	private ResourceTreeItem convertFromTree(ResourceTreeDto tree,List<Resource> userResource){
		if(tree==null)return null;
		ResourceTreeItem item = new ResourceTreeItem();
		item.setId(tree.getResource().getId());
		item.setName(tree.getResource().getName());
		
		//初始化五个操作选项
		Operate readO = new Operate();
		readO.setName("读取");
		readO.setTitle("read");
		readO.setChecked(0);
		item.getOpreate().add(readO);
		Operate addO = new Operate();
		addO.setName("新增");
		addO.setTitle("add");
		addO.setChecked(0);
		item.getOpreate().add(addO);
		Operate updateO = new Operate();
		updateO.setName("修改");
		updateO.setTitle("update");
		updateO.setChecked(0);
		item.getOpreate().add(updateO);
		Operate delO = new Operate();
		delO.setName("删除");
		delO.setTitle("delete");
		delO.setChecked(0);
		item.getOpreate().add(delO);
		Operate allO = new Operate();
		allO.setName("全部");
		allO.setTitle("all");
		allO.setChecked(0);
		item.getOpreate().add(allO);
		
		if(tree.getResource().getActionUrl()!=null&&tree.getResource().getIsLeaf()==1){
			if(tree.getResource().getActionUrl().contains("Add")){
				item.setType(1);
				item.getOpreate().get(1).setDisabled(1);
			}else if(tree.getResource().getActionUrl().contains("Update")){
				item.setType(2);
				item.getOpreate().get(2).setDisabled(1);
			}else if(tree.getResource().getActionUrl().contains("Del")){
				item.setType(3);
				item.getOpreate().get(3).setDisabled(1);
			}else{
				item.setType(0);
				item.getOpreate().get(0).setDisabled(1);
			}
			item.getOpreate().get(4).setDisabled(1);
				
		}
		
		for(ResourceTreeDto t : tree.getChildren()){
			ResourceTreeItem rRTI =convertFromTree(t,userResource); 
			item.getChildren().add(rRTI);

			if(userResource!=null){
				for(Resource r :userResource){
					if(t.getResource().getId().longValue()==r.getId().longValue()){
						if(t.getResource().getActionUrl().contains("Add")){
							item.getOpreate().get(1).setChecked(1);
						}else if(t.getResource().getActionUrl().contains("Update")){
							item.getOpreate().get(2).setChecked(1);
						}else if(t.getResource().getActionUrl().contains("Del")){
							item.getOpreate().get(3).setChecked(1);
						}else{//查看
							item.getOpreate().get(0).setChecked(1);
						}
					}
				}
			}
//			if(item.getId()==36){
//				System.out.println("a");
//			}
			if(rRTI.getOpreate()!=null)
			for(int i=0;i<rRTI.getOpreate().size();i++){
				Operate o = rRTI.getOpreate().get(i);
				if(o.getDisabled()==1){
					item.getOpreate().get(i).setDisabled(1);
					item.getOpreate().get(4).setDisabled(1);
				}
			}
		}
		
		return item;
		
	}
	

	private ResourceTreeDto convertToTree(ResourceTreeDto root,List<Resource> rs) {
		if(root==null){
			root = new ResourceTreeDto();
			for(Resource r :rs ){
				if(r.getId()==1L){//根结点
					root.setResource(r);
				}
			}
		}	
		
		for(Resource r :rs ){
			if(r.getParentId()==null)continue;
			if(r.getParentId().longValue()==root.getResource().getId()){
				ResourceTreeDto dto = new ResourceTreeDto();
				r.setMenus(null);
				r.setRoleResourceRefs(null);
				dto.setResource(r);
				root.getChildren().add(dto);
			}
		}
		
		for(ResourceTreeDto rtd : root.getChildren()){
			convertToTree(rtd,rs);
		}
		
		return root;
	} 
	@Override
	public TreeJSDto resourceTree() {
		TreeJSDto tree = new TreeJSDto();
		HashMap map = new HashMap();
		map.put("status", Constant4Ospm.STATUS_CODE_NORMAL);
		List<Resource> resources = (List<Resource>) this.findByHql(
				"FROM Resource where status=:status", map, 0, 0);
		if (resources.size() > 0) {
			tree.setRoot("div.zm_content");
			TreeItemDto ops = new TreeItemDto();
			ops.setId(resources.get(0).getId());
			ops.setName(resources.get(0).getName());
			ops.setFolderA("资源路径");
			ops.setFolderB("说明");
			ops.setParentId(resources.get(0).getParentId());
			resources.remove(0);
			childTree(ops, resources);
			tree.setOps(ops);
		}
		return tree;
	}

	private void childTree(TreeItemDto ops, List<Resource> resources) {

		for (Resource r : resources) {
			if (r.getParentId().longValue() == ops.getId()) {
				TreeItemDto child = new TreeItemDto();
				child.setFolderA(r.getActionUrl());
				child.setFolderB(r.getDescription());
				child.setParentId(r.getParentId());
				child.setMenuId(r.getId());
				child.setResourceId(new Long(r.getIsLeaf()));
				child.setId(r.getId());
				child.setName(r.getName());
				ops.getChildren().add(child);
			}
		}

		for (TreeItemDto md : ops.getChildren()) {
			childTree(md, resources);
		}

	}

	@Override
	public List<Resource> resouceAll() {
		HashMap map = new HashMap();
		map.put("status", Constant4Ospm.STATUS_CODE_NORMAL);
		map.put("isLeaf", Constant4Ospm.STATUS_CODE_NORMAL);
		return (List<Resource>) resourceDao.findByHql(
				"FROM Resource where status=:status and isLeaf=:isLeaf ", map,
				0, 0);
	}

	@Override
	public String delResource(Resource resource) {
		resource = (Resource) this.resourceDao.load(Resource.class,
				resource.getId());
		resource.setStatus(Constant4Ospm.STATUS_CODE_UNUSE);
		this.resourceDao.saveOrUpdate(resource);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constant4Ospm.STATUS_CODE_UNUSE);
		map.put("parentId", resource.getId());
		this.cudByHql(
				"update Resource set status=:status where parentId=:parentId",
				map);
		return "删除成功";
	}

	@Override
	public String updateResouce(Resource resource) {
		HashMap map = new HashMap();
		map.put("actionUrl", resource.getActionUrl());
		map.put("description", resource.getDescription());
		map.put("id", resource.getId());
		map.put("isLeaf", resource.getIsLeaf());
		map.put("name", resource.getName());
		map.put("parentId", resource.getParentId());
		map.put("status", resource.getStatus());
		this.resourceDao.cudByHql("update Resource set parentId=:parentId,actionUrl=:actionUrl,description=:description,isLeaf=:isLeaf,name=:name,status=:status where id=:id", map);
		return "修改成功";
	}
}
