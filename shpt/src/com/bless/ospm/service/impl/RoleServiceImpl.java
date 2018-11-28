package com.bless.ospm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.RoleDao;
import com.bless.ospm.dao.RoleResourceRefDao;
import com.bless.ospm.dto.Operate;
import com.bless.ospm.dto.ResourceTreeItem;
import com.bless.ospm.dto.ResourceTreeJSDto;
import com.bless.ospm.model.base.Role;
import com.bless.ospm.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService{
	Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	private RoleDao roleDao;
	private RoleResourceRefDao roleResourceRefDao;
	

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		setBaseDao(roleDao);
	}
	
	public void setRoleResourceRefDao(RoleResourceRefDao roleResourceRefDao) {
		this.roleResourceRefDao = roleResourceRefDao;
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}

	@Override
	public void updatePrivilege(Long roleId,String privilegeTreeJS) {
		log.info("method updatePrivilege() called.");
		//将json转化成object
		HashMap<String, Class> map = new HashMap<String, Class>();
		map.put("ops", ResourceTreeItem.class);
		map.put("opreate", Operate.class);
		map.put("children", ResourceTreeItem.class);
		
		ResourceTreeJSDto dto = (ResourceTreeJSDto) JSONObject.toBean(JSONObject.fromObject(privilegeTreeJS),ResourceTreeJSDto.class,map);
		//取出所有的resource编号
		List<Long> result = new ArrayList<Long>();
		getResourceFromDto(dto.getOps(),result);
		
		log.info("resource size is "+result.size()+"");
		for(Long id :result){
			log.info(id+"");
		}
		
//		//result里就是我们要更新的权限编号列表
//		HashMap proMap = new HashMap();
//		proMap.put("rid", roleId);
//		//先删除
//		roleResourceRefDao.cudByHql("Delete From RoleResourceRef where role.id=:rid", proMap);
//		//再插入
//		for(Long rid :result ){
//			RoleResourceRef entity = new RoleResourceRef();
//			Resource r = new Resource();
//			r.setId(rid);
//			Role role = new Role();
//			role.setId(roleId);
//			entity.setResource(r);
//			entity.setRole(role);
//			roleResourceRefDao.saveOrUpdate(entity);
//		}
		
	}
	
	private void getResourceFromDto(List<ResourceTreeItem> items,List<Long> result){
		if(result==null)result = new ArrayList<Long>();
		
		for(ResourceTreeItem item :items){
//			if(item.getId()==19L){
//				System.out.println("fs");
//			}
			//非子节点则跳过
			
			if(item.getType()!=-1&&item.getOpreate().get(0).getDisabled()==1&&item.getOpreate().get(0).getChecked()==1){//查看
				if(item.getChildren().size()==0){
					result.add(item.getId());	
				}else{
					for(ResourceTreeItem it : item.getChildren()){
						if(it.getType()==0)
							result.add(it.getId());
					}
				}
			}else if(item.getType()!=-1&&item.getOpreate().get(1).getDisabled()==1&&item.getOpreate().get(1).getChecked()==1){//新增
				if(item.getChildren().size()==0){
					result.add(item.getId());	
				}else{
					for(ResourceTreeItem it : item.getChildren()){
						if(it.getType()==1)
							result.add(it.getId());
					}	
				}
			}else if(item.getType()!=-1&&item.getOpreate().get(2).getDisabled()==1&&item.getOpreate().get(2).getChecked()==1){//修改
				if(item.getChildren().size()==0){
					result.add(item.getId());	
				}else{
					for(ResourceTreeItem it : item.getChildren()){
						if(it.getType()==2)
							result.add(it.getId());
					}	
				}
			}else if(item.getType()!=-1&&item.getOpreate().get(3).getDisabled()==1&&item.getOpreate().get(3).getChecked()==1){//删除
				if(item.getChildren().size()==0){
					result.add(item.getId());	
				}else{
					for(ResourceTreeItem it : item.getChildren()){
						if(it.getType()==3)
							result.add(it.getId());
					}					
				}
			} 
			
			getResourceFromDto(item.getChildren(),result);
		}
		
		
	}
	
	public static void main(String[] args) {
		String privilegeTreeJS ="{\"root\":\"div.zm_body\",\"ops\":[{\"id\":3,\"name\":\"首页面\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":8,\"name\":\"全球眼\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":35,\"name\":\"权限树\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":36,\"name\":\"系统管理\",\"children\":[{\"id\":33,\"name\":\"修改密码\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":37,\"name\":\"权限管理\",\"children\":[{\"id\":19,\"name\":\"权限首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":20,\"name\":\"权限数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":24,\"name\":\"添加权限\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":25,\"name\":\"修改权限\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":26,\"name\":\"删除权限\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":38,\"name\":\"角色管理\",\"children\":[{\"id\":14,\"name\":\"角色首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":15,\"name\":\"角色数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":27,\"name\":\"添加角色\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":28,\"name\":\"修改角色\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":29,\"name\":\"删除角色\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":39,\"name\":\"用户管理\",\"children\":[{\"id\":17,\"name\":\"用户首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":18,\"name\":\"用户数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":30,\"name\":\"添加用户\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":31,\"name\":\"修改用户\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":32,\"name\":\"删除用户\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":40,\"name\":\"菜单管理\",\"children\":[{\"id\":4,\"name\":\"菜单数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":16,\"name\":\"菜单首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":21,\"name\":\"添加菜单\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":22,\"name\":\"修改菜单\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":23,\"name\":\"删除菜单\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":41,\"name\":\"城管内容管理\",\"children\":[{\"id\":2,\"name\":\"巡逻队员查看\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":5,\"name\":\"执法车查看\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":6,\"name\":\"事件首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":7,\"name\":\"事件数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":9,\"name\":\"人员首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":10,\"name\":\"被处罚人首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":11,\"name\":\"被处罚人列表\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":12,\"name\":\"组织结构首页\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]},{\"id\":13,\"name\":\"组织结构数据\",\"children\":[],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}],\"opreate\":[{\"name\":\"读取\",\"checked\":0},{\"name\":\"新增\",\"checked\":0},{\"name\":\"修改\",\"checked\":0},{\"name\":\"删除\",\"checked\":0},{\"name\":\"全部\",\"checked\":0}]}]}";
		
		ResourceTreeJSDto o = (ResourceTreeJSDto) JSONObject.toBean(JSONObject.fromObject(privilegeTreeJS),ResourceTreeJSDto.class);
		System.out.println(o.getOps().size());
	}

	@Override
	public List<Role> roleAll() {
		HashMap map = new HashMap();
		map.put("status", new Byte("0"));
		List<Role> roles = (List<Role>)this.roleDao.findByHql("from Role where status=:status", map,0,0);
		return roles;
	}
	
}
